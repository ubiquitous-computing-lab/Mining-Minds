/*
 *  Project: Stop Verbosity
 *  Description: Limits and counts the characters in a textarea.
 *  Author: William Huey
 *  License: MIT
 *  Version: 1.14.0
 *
 *  Credits:
 *  Tim Down (getInputSelection, setInputSelection)
 *    http://stackoverflow.com/questions/3549250/highlighting-a-piece-of-string-in-a-textarea
 *  Ben Alpert(isInputSupported)
 *    http://benalpert.com/2013/06/18/a-near-perfect-oninput-shim-for-ie-8-and-9.html
 *
 */
 
;(function($, window, document, undefined) {
  'use strict';
  //Quick disabling of logging
  //var console = {};
  //console.log = function(){}; 
  var pluginName = 'stopVerbosity';
  var PluginWrapper = function(element, options) {
    //Defaults options
    var defaults = {
      limit: 10,
      indicatorPosition: 'after',
      indicatorId: '',
      indicatorElementType: 'p',
      indicatorPhrase: ['Used', '[countup]', 'of', '[limit]', 'characters.',
        '[countdown]', 'characters remaining.', 'The maximum is', '[limit]',
        'characters.', '<br>', 'Permits multiple counts up:', '[countup]',
        'and counts down:', '[countdown].',
        'This indicator is customizable.'
      ],
      indicatorUpdateSpeed: 0,
      existingIndicator: '',
      generateIndicator: true,
      showIndicator: true,
      useNativeMaxlength: true,
      textLengthChange: false,
      useMaxlength: true,
      beforeAttachment: null,
      afterAttachment: null,
      existingText: 'clear'
    };
    //Initializing main plugin with options
    var plugin = function(element, options) {
      plugin.element = element;
      plugin.options = $.extend({}, defaults, options);
    };
    var plugProto = plugin.prototype = {
      init: function() {
        var $el = $(element);
        //SetupActions is the initialization process
        //E.g., checks for options input, inserts indicator (counter)
        //Also add event listeners to the element
        var setupActionData = plugProto.setupActions().init($el, plugin.options);
        plugProto.eventsActions($el).init(plugin.options.limit, setupActionData);
      },
      setupActions: function() {
        //The initialization object for the plugin object
        var Setup = function() {};
        Setup.prototype = {
          init: function($el, options) {
            //Callback after all elements with events 
            //are created and attached
            try {
              if (Object.prototype.toString.call(options.beforeAttachment) ===
                '[object Function]') {
                options.beforeAttachment.call($el);
              }
            } catch (error) {
              throw new TypeError('beforeAttachment is not a proper function.');
            }

            //Polyfill indexOf, for Ie
            //https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/indexOf
            if (!Array.prototype.indexOf) {
              Array.prototype.indexOf = function(searchElement, fromIndex) {
                if (this == null) {
                  throw new TypeError();
                }
                var t = Object(this);
                var len = t.length >>> 0;
                if (len === 0) {
                  return -1;
                }
                var n = 0;
                if (arguments.length > 1) {
                  n = Number(arguments[1]);
                  if (n != n) { //shortcut for verifying if it's NaN
                    n = 0;
                  } else if (n != 0 && n != Infinity && n != -Infinity) {
                    n = (n > 0 || -1) * Math.floor(Math.abs(n));
                  }
                }
                if (n >= len) {
                  return -1;
                }
                var k = n >= 0 ? n : Math.max(len - Math.abs(n), 0);
                for (; k < len; k++) {
                  if (k in t && t[k] === searchElement) {
                    return k;
                  }
                }
                return -1;
              };
            }

            //Alias and expose options to prototype
            var limit = options.limit,
              indicatorPosition = options.indicatorPosition,
              indicatorId = options.indicatorId,
              indicatorElementType = options.indicatorElementType,
              indicatorPhrase = options.indicatorPhrase,
              existingIndicator = options.existingIndicator,
              generateIndicator = options.generateIndicator,
              showIndicator = options.showIndicator,
              useNativeMaxlength = options.useNativeMaxlength,
              existingText = options.existingText;
            //Quick reference for the plugin prototype and dataStore
            var setupProto = Setup.prototype,
              setupDataStore = setupProto.setupDataStore;
            //Store a few settings into dataStore to pass to the eventActions
            setupDataStore.useNativeMaxlength = useNativeMaxlength;
            setupDataStore.indicatorUpdateSpeed = options.indicatorUpdateSpeed;
            setupDataStore.textLengthChange = options.textLengthChange;
            setupDataStore.useMaxlength = options.useMaxlength;
            setupDataStore.showIndicator = showIndicator;
            setupDataStore.afterAttachment = options.afterAttachment;
            setupDataStore.existingText = options.existingText;
            //Check character limit value in textarea
            setupProto.checkLimitOption(limit);
            //Check for clearing of text in textarea
            //Insert the indicator (character counter) near textarea
            setupProto.setIndicator(limit, indicatorElementType,
              indicatorId, indicatorPosition, indicatorPhrase, $el,
              existingIndicator, generateIndicator, showIndicator,
              existingText);
            //After initializing, return phrase info
            return setupProto.setupDataStore;
          },
          checkLimitOption: function(limit) {
            //Check the supply word limit
            if (typeof limit !== 'number') {              
              if (parseFloat(limit) == parseInt(limit, 10) && 
                !isNaN(limit) && limit > 0) {
                throw new RangeError('Text limit is not a valid positive integer.');
              } else {
                throw new TypeError('Specified text limit is not a number.');
              }
            }
          },
          setIndicator: function(limit, indicatorElementType,
            indicatorId, indicatorPosition, indicatorPhrase, $el,
            existingIndicator, generateIndicator,
            showIndicator, existingText) {
            //Only use an indicator when set to true
            if (!showIndicator) {
              return;
            }
            //Get the 'this' reference
            var _this = this;

            function isStrVarFound(keys, indicatorPhrase, context) {
              //Go through every keyvar
              for (var i = 0, keysLen = keys.length; i < keysLen; i++) {
                //Track the current key          
                var currentKey = keys[i],
                  currentKeyLen = currentKey.length,
                  strVar = '[' + currentKey + ']',
                  regexPattern = '\\[' + currentKey + '\\]',
                  coupledStrVar = currentKey + "Coupled",
                  j = 0,
                  indicatorLen = indicatorPhrase.length,
                  regex = new RegExp(regexPattern);
                //Set up object for coupled strVar in the indicatorPhrase
                //eg. '[limit].', '"[countdown]"' 
                coupledStrVar = context.setupDataStore[coupledStrVar] = {};
                //Look through the whole array for current keyvar 
                for (; j < indicatorLen; j++) {
                  var phraseItem = indicatorPhrase[j];
                  //Comparing each phrase item to keyvar
                  if (phraseItem === strVar) {
                    //Plain strVar case
                    context.setupDataStore[currentKey].push(j);
                  } else if (regex.test(phraseItem)) {
                    //Track count of the strVar in each phraseItem
                    var k = phraseItem.indexOf(strVar),
                      foundCount = [];
                    //Put the first index found into the array
                    foundCount.push(k);
                    while (k > -1) {
                      k = phraseItem.indexOf(strVar, k + 1);
                      if (k > -1) {
                        //Account for all the repeats
                        foundCount.push(k);
                      }
                    }
                    //Only perform the update when there is only one strVar
                    if (foundCount.length === 1) {
                      //Use a regex check if the keyvar is not an exact match
                      //Put the index for each coupled strVar
                      var coupledStrVarIndex = coupledStrVar[j] = {};
                      //Starting index for the found strVar
                      var regexIndex = regex.exec(phraseItem).index;
                      //Phrase indexes of the neighboring text of the phraseItem
                      var frontPhrase = phraseItem.slice(0, regexIndex),
                        backStart = regexIndex + currentKeyLen + 2,
                        phraseItemLen = phraseItem.length,
                        backPhrase = phraseItem.slice(backStart,
                          phraseItemLen);
                      switch (currentKey) {
                        case 'limit':
                        case 'countdown':
                          indicatorPhrase[j] = frontPhrase + limit +
                            backPhrase;
                          break;
                        case 'countup':
                          indicatorPhrase[j] = frontPhrase + '0' + backPhrase;
                          break;
                      }
                      var backDifference = phraseItemLen - backStart;
                      //Only need the first part ending index
                      //because the first part always starts at 0
                      coupledStrVarIndex.firstEnd = regexIndex;
                      //Only need the difference because the phraseItem will change in length
                      //Difference will only account for the contents to the right of the changed 
                      coupledStrVarIndex.backDifference = backDifference;
                    }
                  }
                }
              }
            }
            //Search for variables, strVar, that are defined in the indicator phrase
            isStrVarFound(['limit', 'countup', 'countdown'], indicatorPhrase,
              _this);

            function updatePhrase(key, data, indicatorPhrase, _this) {
              //Go through each key (strVar)
              for (var i = 0, keyLen = key.length; i < keyLen; i++) {
                var currentKey = key[i],
                  phraseArray = _this.setupDataStore[currentKey],
                  phraseItems = phraseArray.length,
                  j = 0;
                //Update the locations that have each key
                for (; j < phraseItems; j++) {
                  var pIndex = phraseArray[j];
                  //Countup starts at zero
                  //while countdown and limit starts at the limit
                  switch (currentKey) {
                    case 'countup':
                      //Count up starting from zero
                      indicatorPhrase[pIndex] = 0;
                      break;
                    default:
                      //Counting down and the limit starts from the limit
                      data.phrase[pIndex] = data.limit;
                      break;
                  }
                }
              }
            }
            //Set the right value for limit, countup, and countdown
            var limitData = {
              phrase: indicatorPhrase,
              limit: limit
            };
            //Initial update on the phrase with the strVar
            updatePhrase(['limit', 'countdown', 'countup'],
              limitData, indicatorPhrase, _this);
            //Also get a copy of the original phrase array
            this.setupDataStore.indicatorPhrase = indicatorPhrase;
            //Use an element that is already on the page
            //Check for jQuery object passed in
            if (Object.prototype.toString.call(existingIndicator) ===
              '[object Object]') {
              try {
                if (existingIndicator instanceof jQuery) {
                  this.setupDataStore.indicatorElement = existingIndicator;
                  existingIndicator.html(indicatorPhrase.join(' '));
                  generateIndicator = false;
                }
              } catch (error) {
                throw new TypeError('Not a jQuery Object.');
              }
            }
            //Generate the indicator if no default element is given
            if (generateIndicator) {
              //Check if id is given
              var id = indicatorId.length > 0 ? ' id=' + indicatorId : '';
              //Insert the indicator onto the page
              var indicatorElement = [
                '<', indicatorElementType, id, '>',
                indicatorPhrase.join(' '), '</', indicatorElementType, '>'
              ].join(''),
                indicatorError = [
                  'Not a valid position for indicator,', 'only accepts ',
                  "'before'", ' or ', "'after'"
                ].join('');
              //Specify the ordering of the indicator
              if (indicatorPosition === 'after') {
                $el.after(indicatorElement);
                this.setupDataStore.indicatorElement = $el.next();
              } else if (indicatorPosition === 'before') {
                $el.before(indicatorElement);
                this.setupDataStore.indicatorElement = $el.prev();
              } else {
                throw new Error(indicatorError);
              }
            }

          },
          setupDataStore: {
            limit: [],
            countup: [],
            countdown: []
          }
        };
        return Setup.prototype;
      },
      eventsActions: function($el) {
        //The events object for the plugin object
        var Events = function() {},
          ep = Events.prototype = {
            init: function(limit, setupActionData) {
              //Reference the Events prototype
              //Add event listeners to the textarea
              ep.addListeners(limit, setupActionData);
            },
            addListeners: function(limit, setupActionData) {
              //Alias the dataStore
              var ds = this.dataStore;
              //Set commonly accessed items to the datastore
              ds.limit = limit;
              ds.indicator = setupActionData;
              var indicatorUpdateSpeed = setupActionData.indicatorUpdateSpeed;
              var existingText = setupActionData.existingText;
              //For key prevention and allowance
              var input = {
                preventFunction: function() {
                  //See if keydown prevent is already used
                  //If no keyprevent present
                  if (ds.eventPrevent === null && !ds.isTextSelected) {
                    //Track the prevented events and removed events
                    ep.setDataStore('eventPrevent', 'keydown');
                    ep.removeDataStore('eventPresent');
                    //Remove the keydown listener
                    $el.off('keydown.' + pluginName);
                    //Prevent default on certain keypresses
                    ep.keydownFunction.keydownPrevent();
                    //Prevent pasting
                    ep.pastePreventFunction();
                  }
                },
                allowFunction: function() {
                  //See if keydown prevent is already used
                  if (ds.eventPrevent !== null) {
                    //Track the prevented events and removed events
                    ep.removeDataStore('eventPrevent');
                    ep.setDataStore('eventPresent', 'keydown');
                    //Remove the event prevent on keydown
                    //and prevent paste function
                    $el.off('keydown.' + pluginName).off('paste.' +
                      pluginName);
                    //Restore the original keydown
                    ep.keydownFunction.keydown();
                  }
                },
                changeText: function(countArray) {
                  var indicator = ds.indicator,
                    i = 0,
                    countArrayLen = countArray.length;
                  //Go through all strVars  
                  for (; i < countArrayLen; i++) {
                    var countType = countArray[i],
                      countDirection = indicator[countType];
                    if (typeof countDirection !== 'undefined') {
                      var objectType = Object.prototype.toString.call(
                        countDirection);
                      //Check if an object for strVar with surrounding text
                      if (objectType === '[object Object]') {
                        //All keys are numbers
                        //Countdirection will be an object of indexes for strVars
                        for (var key in countDirection) {
                          if (countDirection.hasOwnProperty(key)) {
                            var phraseItem = indicator.indicatorPhrase[key],
                              phraseLen = phraseItem.length,
                              keyContent = countDirection[key];
                            //Get the old text from stored phrase
                            var firstPart = phraseItem.slice(0, keyContent.firstEnd),
                              lastFirstIndex = phraseLen - keyContent.backDifference,
                              lastBackIndex = keyContent.backDifference +
                                phraseLen,
                              lastPart = phraseItem.slice(lastFirstIndex,
                                lastBackIndex);
                            var newPhraseItem;
                            if (countType === 'countdownCoupled') {
                              textAmount = limit - ep.getTextAreaLength();
                              newPhraseItem = firstPart + textAmount +
                                lastPart;
                            } else if (countType === 'countupCoupled') {
                              textAmount = ep.getTextAreaLength();
                              newPhraseItem = firstPart + textAmount +
                                lastPart;
                            }
                            //Update the phrase item in the phrase
                            indicator.indicatorPhrase[key] = newPhraseItem;
                          }
                        }
                      } else if (objectType === '[object Array]') {
                        //For keyVars that are not coupled
                        var textAmount;
                        //The difference of characters for the countdown
                        if (countType === 'countdown') {
                          textAmount = limit - ep.getTextAreaLength();
                        } else if (countType === 'countup') {
                          textAmount = ep.getTextAreaLength();
                        }
                        //Get the index of the countType
                        var strIndice = indicator[countType],
                          strIndexLen = strIndice.length,
                          j = 0;
                        for (; j < strIndexLen; j++) {
                          //Update items in the phrase
                          indicator.indicatorPhrase[strIndice[j]] =
                            textAmount;
                        }
                      }
                    }
                  }
                  //Overwrite the older text for indicator on the page
                  //Use a timer if a integer value is given 
                  if (indicatorUpdateSpeed > 0) {
                    setTimeout(function() {
                      indicator.indicatorElement.html(indicator.indicatorPhrase
                        .join(" "));
                    }, indicatorUpdateSpeed);
                  } else {
                    indicator.indicatorElement.html(indicator.indicatorPhrase
                      .join(" "));
                  }
                },
                //Provides an event for change in content length within textarea
                textLengthChange: function(lastTextLength) {
                  if (setupActionData.textLengthChange) {
                    var currentTextLength = ep.getTextAreaLength();
                    if (currentTextLength !== lastTextLength) {
                      tlcObj.currentTextLength = currentTextLength;
                      tlcObj.lastTextLength = lastTextLength;
                      if (currentTextLength < limit) {
                        tlcObj.isLimitReached = false;
                        tlcObj.exceededLimit = false;
                      } else if (currentTextLength === limit) {
                        tlcObj.isLimitReached = true;
                        tlcObj.exceededLimit = false;
                      } else if (currentTextLength > limit) {
                        tlcObj.isLimitReached = true;
                        tlcObj.exceededLimit = true;
                      }
                      //Pass in information to the text length change event
                      $el.trigger('sv-textLengthChange', tlcObj);
                    }
                  }
                }
              };
              //Object for textLengthChange to provide information 
              var tlcObj = input.textLengthChange.obj = {};
              //Attach element information to the tlcObj object
              tlcObj.element = plugin.element;
              if (ds.indicator.indicatorElement) {
                tlcObj.indicatorElement = ds.indicator.indicatorElement;
              }
              //Create a textarea element for detecting support of attributes
              var textareaElement = document.createElement('textarea'),
                checkFirstKeyDown;
              //Test for input or propertychange support in textarea            
              if ('oninput' in textareaElement) {
                //Partial support but still needed to check for full support
                ds.partialInputSupport = true;
                if (!('documentMode' in document) || document.documentMode > 9) {
                  //Full input support
                  checkFirstKeyDown = false;
                  ds.supportsInput = true;
                  ds.partialInputSupport = false;
                }
              } else if ('onpropertychange' in textareaElement) {
                //Only check support for onpropertychange
                checkFirstKeyDown = true;
                ds.supportsInput = false;
              }
              //Store check condition in datastore
              ds.checkFirstKeyDown = checkFirstKeyDown;
              //Attach the attribute maxlength if supported
              if ( !! ('maxLength' in textareaElement)) {
                //Check for use of native maxlength
                if (setupActionData.useNativeMaxlength &&
                  setupActionData.useMaxlength) {
                  $el.attr('maxlength', limit);
                }
              }
              //Helper for setInputSelection
              ep.setInputSelection.offsetToRangeCharacterMove = function(el, offset) {
                return offset - (el.value.slice(0, offset).split('\r\n').length - 1);
              };
              //Attach initial set of event listeners
              ep.deselectFunction(input);
              ep.selectFunction(input);
              ep.dropFunction();
              ep.inputFunction(input);
              ep.trackSelectionEventsFunction();
              ep.mousedownFunction();
              ep.clickFunction();
              ep.keydownFunction();

              //Clear out the textarea upon refresh
              if (existingText === 'clear') {
                $el.val("");
              } else if (existingText === 'truncate') {
                //Existing text will be truncated it there is text to be preserved
                //that exceeds the limit
                //Only trigger change if there is text
                if ($el.val().length > 0) {
                  $el.trigger('propertychange.' + pluginName);
                }
              } else {
                throw new Error('Must be string value of "clear" or "truncate".');
              }

              //Callback after all elements with events 
              //are created and attached
              try {
                if (Object.prototype.toString.call(setupActionData.afterAttachment) ===
                  '[object Function]') {
                  setupActionData.afterAttachment.call($el, tlcObj);
                }
              } catch (error) {
                throw new TypeError('afterAttachment is not a proper function.');
              }
            },
            inputFunction: function(input) {
              var ds = ep.dataStore,
                strVars = ['countdown', 'countup', 
                  'countdownCoupled','countupCoupled'
                ],
                tempObj = {};
              //Function to update the indicator if appropriate
              var updateIndicator = function() {
                if (ds.indicator.showIndicator) {
                  //Determines text changes and the speed of change
                  input.changeText(strVars);
                  //Get the value from the object for prior text length 
                  ep.copyObjectValue(ds, tempObj, 'lengthText');
                  //Update content length
                  ep.setDataStore('lengthText', ep.getTextAreaLength());
                  //Run to see if the text length change needs to be trigger
                  input.textLengthChange(tempObj.lengthText);
                } else {
                  //Still update datastore when no indicator is found
                  ep.setDataStore('lengthText', ep.getTextAreaLength());
                }
              };
              //Input change listener
              $el.on('input.' + pluginName + ' ' + 'propertychange.' +
                pluginName, function() {
                  //CheckFirstKeyDown for Ie8
                  ds.checkFirstKeyDown = false;
                  //To determine drop change
                  if (ep.dataStore.dropOccurred) {
                    ep.dataStore.dropOccurred = false;
                    return;
                  }
                  //Get the limit
                  var limit = ds.limit,
                    //See if limit is reached in textarea
                    status = ep.checkLimit(limit);
                  ep.setDataStore('textStatus', status);
                  //Less than the limit
                  if (status === 'less') {
                    //Have not reach the limit yet
                    //Check for eventprevent from reaching the limit
                    if (ds.falsePositive) {
                      input.allowFunction();
                    }
                    //For updating the indicator if there is one
                    updateIndicator();
                    //Have to use a timer because of a Chrome bug
                    //http://code.google.com/p/chromium/issues/detail?id=32865
                    //Have to use timer or else Chrome will not track selection
                    //on input change event
                    setTimeout(function() {
                      ep.trackSelectionFunction();
                    });
                  } else if (status === 'equal') {
                    //Equal to the limit, prevent key presses
                    if (ds.falsePositive) {
                      if (ds.indicator.useMaxlength) {
                        input.preventFunction();
                      }
                    }
                    //For updating the indicator if there is one
                    updateIndicator();
                    setTimeout(function() {
                      ep.trackSelectionFunction();
                      if (ds.falsePositive) {
                        //Only need to reset the caret when exceeded length from pasting
                        if (typeof ep.dataStore.newCaretPos === 'number') {
                          var newCaretPos = ep.dataStore.newCaretPos;
                          ep.setInputSelection(newCaretPos, newCaretPos);
                          ep.dataStore.newCaretPos = null;
                        }
                      }
                    });
                  } else if (status === 'greater') {
                    //In case there is a false positive
                    //for maxlength support
                    ds.falsePositive = true;
                    //Correction for Ie6-8
                    //to properly detect text selection prior to pasting 
                    if (ds.selectBeforeKeydown) {
                      ds.caretStatus = ds.selectCaretStatus;
                    }
                    //Only calculate the text to restore when
                    //plugin is set to use maxlength
                    if (ds.indicator.useMaxlength) {
                      var newLength = ep.getTextAreaLength(),
                        oldCaret = ds.caretStatus,
                        oldCaretStart = oldCaret.start,
                        oldCaretEnd = oldCaret.end,
                        oldLength = ds.lengthText,
                        charactersLeft = limit - oldLength,
                        textString = ep.getText();
                      //For the new phrase
                      var frontEnd,
                        backFront,
                        middleFront,
                        middleBack;
                      //Text was selected before
                      if (ds.isTextSelected || ds.selectBeforeKeydown) {
                        frontEnd = oldCaret.end;
                        middleFront = oldCaret.end;
                        middleBack = charactersLeft + oldCaretEnd;
                        backFront = newLength - (oldLength - oldCaretEnd);
                      } else {
                        //Text was not selected before
                        frontEnd = oldCaretStart;
                        middleFront = oldCaretStart;
                        middleBack = charactersLeft + oldCaretStart;
                        backFront = newLength - (oldLength - oldCaretStart);
                      }
                      //Correct the string when the length is exceeded
                      var front = textString.slice(0, frontEnd),
                        middle = textString.slice(middleFront, middleBack),
                        back = textString.slice(backFront, newLength),
                        revertString = front + middle + back,
                        newCaretPos = front.length + middle.length;
                      //Track the new caret position
                      ds.newCaretPos = newCaretPos;
                      //Revert back to the old text when reaching limit
                      if (revertString.length <= limit) {
                        $el.val(revertString);
                        //Force an input event to allow text handling
                        $el.trigger('propertychange.' + pluginName);
                      }
                    } else {
                      //Only need to update the indicator if using only the counter
                      //Maxlength was not used
                      updateIndicator();
                    }
                  }
                });
              //A test for partialInputSupport for Ie9
              if (ds.partialInputSupport) {
                //For Ie9 where deleting text does not trigger input
                $(document).on('selectionchange.' + pluginName, function() {
                  if (ep.getTextAreaLength() < ds.lengthText) {
                    $el.trigger('propertychange.' + pluginName);
                  }
                });
              }
            },
            dropFunction: function() {
              //Alias dataStore
              var ds = ep.dataStore;
              $el.on('drop.' + pluginName, function() {
                //Check to prevent triggering for Ie
                ep.dataStore.dropOccurred = true;
                //Using a timer as a way to get caret after drop event
                setTimeout(function() {
                  //Track the dragged in text 
                  //Text could be selected at the time
                  ep.trackSelectionFunction();
                  var oldCs = ds.caretStatus,
                    start = oldCs.start,
                    end = oldCs.end,
                    finalCaret;
                  //Set the old caret which is the drop text position
                  if (start !== end) {
                    //Text was highlighted after drag
                    ds.caretStatus.start = start;
                    ds.caretStatus.end = start;
                    finalCaret = end;
                  } else {
                    //Text was not selected after drag
                    var oldLen = ds.lengthText,
                      charDiff = $el.val().length - oldLen,
                      adjustStart = start - (charDiff);
                    ds.caretStatus.start = adjustStart;
                    ds.caretStatus.end = adjustStart;
                    finalCaret = start;
                  }
                  //Adjust the new caret as if the text was pasted
                  ep.setInputSelection(finalCaret, finalCaret);
                  //To ensure that input handler will process
                  ep.dataStore.dropOccurred = false;
                  //Input was not prevented from occurring at last so now run it
                  $el.trigger('input.' + pluginName);
                });
              });
            },
            pastePreventFunction: function() {
              $el.on('paste.' + pluginName, function(evt) {
                ep.negateEvent(evt);
              });
            },
            deselectFunction: function(input) {
              $el.on('stopVerbosity-deselect', function() {
                var limit = ep.dataStore.limit,
                  status = ep.checkLimit(limit);
                ep.trackSelectionFunction();
                if (status === 'equal') {
                  //If no keyprevent is present
                  if (ep.dataStore.indicator.useMaxlength) {
                    input.preventFunction();
                  }
                } else if (status === 'greater') {
                  //For Ie8
                  $el.trigger('propertychange.' + pluginName);
                }
              });
            },
            selectFunction: function(input) {
              $el.on('select.' + pluginName, function() {
                //Prevent triggers on dragging of text into textarea for Opera 12
                if (ep.dataStore.dropOccurred) {
                  return;
                }
                //Track the caret position
                ep.trackSelectionFunction();
                var caretStatus = ep.dataStore.caretStatus,
                  limit = ep.dataStore.limit,
                  status = ep.checkLimit(limit);
                //Track caret status that is only for selection
                var oldCaretStart = caretStatus.start,
                  oldCaretEnd = caretStatus.end,
                  selectCaretStatus = ep.dataStore.selectCaretStatus;
                selectCaretStatus.start = oldCaretStart;
                selectCaretStatus.end = oldCaretEnd;
                //Allow or prevent input when equal to limit
                if (status === 'equal') {
                  //Check for Opera 12
                  if (caretStatus.end === caretStatus.start) {
                    if (ep.dataStore.indicator.useMaxlength) {
                      input.preventFunction();
                    }
                  } else {
                    input.allowFunction();
                  }
                }
              });
            },
            trackSelectionEventsFunction: function() {
              var trackSelectionEvts = ['mouseout', 'focus', 'blur', 'keyup'],
                groupEvts = ep.appendNameSpace(trackSelectionEvts, pluginName);
              $el.on(groupEvts, function() {
                //Track if selection is present
                setTimeout(function() {
                  ep.trackSelectionFunction();
                });
              });
            },
            appendNameSpace: function(theArray, namespace) {
              var arrayLen = theArray.length,
                i = 0;
              for (; i < arrayLen; i++) {
                theArray[i] = theArray[i] + '.' + namespace;
              }
            },
            mousedownFunction: function() {
              $el.on('mousedown.' + pluginName, function() {
                //Track mousedown event
                ep.setDataStore('isMouseDown', true);
              });
            },
            clickFunction: function() {
              $el.on('click.' + pluginName, function() {
                //Track if selection is present
                setTimeout(function() {
                  ep.trackSelectionFunction();
                });
                //Track mouse up from click
                ep.setDataStore('isMouseDown', false);
              });
            },
            keydownFunction: function() {
              var ds = ep.dataStore;
              //To correct for Ie8 for not triggering on change
              this.keydownFunction.checkFirstKeyDown =
                function(caller) {
                  //Checks the first keydown
                  if (ds.checkFirstKeyDown) {
                    if (ds.continueKeyCheck) {
                      setTimeout(function() {
                        if (ep.getTextAreaLength() > 0) {
                          $el.trigger('propertychange.' + pluginName);
                        }
                      });
                    }
                  }
                  //When using backspace to delete
                  if (caller === 'keyprevent') {
                    setTimeout(function() {
                      $el.trigger('propertychange.' + pluginName);
                    });
                  }
              };
              this.keydownFunction.checkTab = function(evt) {
                if (evt.which === 9) {
                  //If the mouse is down while tab was pressed
                  //prevent it from happening
                  //Done to normalize behavior across browsers
                  if (ep.dataStore.isMouseDown) {
                    ep.negateEvent(evt);
                    return true;
                  }
                }
              };
              //The keydown prevent function when limit is reached
              this.keydownFunction.keydownPrevent = function() {
                $el.on('keydown.' + pluginName, function(evt) {
                  //Preserve some keys after textarea is full: f-keys, arrows, backspace etc.
                  //Code to keys chart http://pastebin.com/z5LH4x9f
                  switch (evt.which) {
                    case 0: case 8 :case 9: case 16: case 17: case 18: case 19:
                    case 20: case 27: case 33: case 34: case 35: case 36: 
                    case 37: case 38: case 39: case 40: case 45: case 46:
                    case 112: case 113: case 114: case 115: case 116: case 117:
                    case 118: case 119: case 120: case 121: case 122: case 123:
                      ep.keydownFunction.checkTab(evt);
                      ep.keydownFunction.checkFirstKeyDown('keyprevent');
                      ds.falsePositive = true;
                      break;
                    default:
                      if (evt.ctrlKey === false) {
                        //On single key presses
                        //Only permit the single key if ctrl was pressed too
                        evt.preventDefault();
                      }
                      break;
                  }
                  setTimeout(function() {
                    ep.trackSelectionFunction();
                    if (ep.dataStore.isTextSelected) {
                      ep.dataStore.selectBeforeKeydown = true;
                    } else {
                      ep.dataStore.selectBeforeKeydown = false;
                    }
                  });
                });
              };
              //Remove the keydown listener to prevent
              //Multiple keydown listener from being attached
              $el.off('keydown.' + pluginName);
              //Default tracking of keydown listener
              this.keydownFunction.keydown = function() {
                $el.on('keydown.' + pluginName, function(evt) {
                  ep.keydownFunction.checkTab(evt);
                  ep.keydownFunction.checkFirstKeyDown();
                  setTimeout(function() {
                    ep.trackSelectionFunction();
                    //For Ie6-8
                    if (ep.dataStore.isTextSelected) {
                      ep.dataStore.selectBeforeKeydown = true;
                    } else {
                      ep.dataStore.selectBeforeKeydown = false;
                    }
                  });
                });
              };
              //Run the keydown tracker
              this.keydownFunction.keydown();
            },
            dataStore: {
              newCaretPos: null,
              caretStatus: {
                start: 0,
                end: 0
              },
              selectCaretStatus: {
                start: 0,
                end: 0
              },
              dropOccurred: false,
              isMouseDown: false,
              isTextSelected: false,
              isLimitReached: false,
              textStatus: null,
              lengthText: 0,
              keyDownPresent: false,
              click: false,
              //Only to track the keydown
              eventPresent: 'keydown',
              eventPrevent: null,
              falsePositive: false,
              checkFirstKeyDown: false,
              continueKeyCheck: true,
              selectBeforeKeydown: false,
              supportsInput: null
            },
            removeDataStore: function(key) {
              this.dataStore[key] = null;
            },
            setDataStore: function(key, value) {
              this.dataStore[key] = value;
            },
            copyObjectValue: function(fromObj, toObj, key) {
              for (var prop in fromObj) {
                if (fromObj.hasOwnProperty(prop)) {
                  if (key === prop) {
                    toObj[key] = fromObj[key];
                  }
                }
              }
            },
            negateEvent: function(evt) {
              //Prevent and stop propagation of event
              evt.preventDefault();
              evt.stopPropagation();
            },
            isTextSelected: function(caretStatus) {
              //A difference indicates a range, which means a selection
              if (Math.abs(caretStatus.start - caretStatus.end) > 0) {
                return true;
              }
            },
            setSelectionStatus: function() {
              //Determine if text is actually selected and sets the status
              var ds = this.dataStore;
              ds.caretStatus = ep.getInputSelection();
              if (ep.isTextSelected(ds.caretStatus)) {
                this.setDataStore('isTextSelected', true);
              } else {
                this.setDataStore('isTextSelected', false);
              }
            },
            trackSelectionFunction: function() {
              //Save the previous state text selection
              var prevSelection = ep.dataStore.isTextSelected;
              //Track if selection is present
              ep.setSelectionStatus();
              //Get the current state text selection
              var currentSelection = ep.dataStore.isTextSelected;
              //Trigger the deselect
              if (prevSelection && currentSelection === false) {
                $el.trigger('stopVerbosity-deselect');
              }
            },
            getText: function() {
              return $el.val();
            },
            getTextAreaLength: function() {
              return this.getText().length;
            },
            checkLimit: function(limit) {
              var charactersLength = this.getTextAreaLength();
              if (charactersLength > limit) {
                return 'greater';
              } else if (charactersLength === limit) {
                return 'equal';
              } else if (charactersLength < limit) {
                return 'less';
              }
            },
            setInputSelection: function(startOffset, endOffset) {
              var _this = this.setInputSelection;
              var el = $el[0];
              if (typeof el.selectionStart === 'number' &&
                typeof el.selectionEnd ===
                'number') {
                el.selectionStart = startOffset;
                el.selectionEnd = endOffset;
              } else {
                var range = el.createTextRange();
                var startCharMove = _this.offsetToRangeCharacterMove(el,
                  startOffset);
                range.collapse(true);
                if (startOffset === endOffset) {
                  range.move('character', startCharMove);
                } else {
                  range.moveEnd('character', _this.offsetToRangeCharacterMove(
                    el, endOffset));
                  range.moveStart('character', startCharMove);
                }
                range.select();
              }
            },
            getInputSelection: function() {
              var start = 0,
                end = 0,
                normalizedValue,
                range,
                textInputRange,
                len,
                endRange,
                el = $el[0];
              if (typeof el.selectionStart === 'number' &&
                typeof el.selectionEnd ===
                'number') {
                start = el.selectionStart;
                end = el.selectionEnd;
              } else {
                range = document.selection.createRange();
                if (range && range.parentElement() === el) {
                  len = el.value.length;
                  normalizedValue = el.value.replace(/\r\n/g, '\n');
                  // Create a working TextRange that lives only in the input
                  textInputRange = el.createTextRange();
                  textInputRange.moveToBookmark(range.getBookmark());
                  // Check if the start and end of the selection are at the very end
                  // of the input, since moveStart/moveEnd doesn't return what we want
                  // in those cases
                  endRange = el.createTextRange();
                  endRange.collapse(false);
                  if (textInputRange.compareEndPoints('StartToEnd', endRange) > - 1) {
                    start = end = len;
                  } else {
                    start = -textInputRange.moveStart('character', -len);
                    start += normalizedValue.slice(0, start).split('\n').length - 1;
                    if (textInputRange.compareEndPoints('EndToEnd', endRange) > - 1) {
                      end = len;
                    } else {
                      end = -textInputRange.moveEnd('character', -len);
                      end += normalizedValue.slice(0, end).split('\n').length - 1;
                    }
                  }
                }
              }
              return {
                start: start,
                end: end
              };
            }
          };
        return Events.prototype;
      }
    };
    //Initialize the plugin
    plugin(element, options);
    plugin.prototype.init();
  };
  $.fn[pluginName] = function(options) {
    return this.each(function() {
      if (!$.data(this, "plugin_" + pluginName)) {
        $.data(this, "plugin_" + pluginName, new PluginWrapper(this, options));
      }
    });
  };
})(jQuery, window, document);