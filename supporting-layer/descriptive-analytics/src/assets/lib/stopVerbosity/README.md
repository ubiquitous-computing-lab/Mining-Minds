Stop Verbosity - MaxLength for Textarea
=========================================

Description: 
-------------
Stop Verbosity is a jQuery plugin that limit the amount of text that is permitted in a textarea element by replicating the maxlength attribute for the textarea. A line of text, the indicator/counter, counts characters used or remaining when text is entered into the textarea. Once the limit is reached, there is no more text input from typing, pasting or drag and drop.

[Online Demo](http://williamhuey.github.io/code/stopverbosity)

Default Options:
-------------
```bash
<script>
$(document).ready(function () {
  $('textarea').stopVerbosity({
    limit: 10,
    indicatorPosition: 'after',
    indicatorId: '',
    indicatorElementType: 'p',
    indicatorPhrase: ['Used', '[countup]', 'of', '[limit]',
      'characters.',
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
  });
})
</script>
```

Option Details:
-------------
###limit (integer)
    The number of characters that are allowed in the textarea.

###indicatorPosition (string)
    Could be 'before' or 'after'. 
    'before' inserts the indicator before the textarea.
    'after' inserts the indicator after the textarea.
	
###indicatorId (string)	
    Optional id if you wish to quickly refer to the indicator for styling.

###indicatorElementType (string)
    Should be either a 'p' or 'span' tag since the indicator is text.

###indicatorPhrase (array of strings)
    Specify your custom text with quotes for an item in the array. 
    '[countup]', '[countdown]', and '[limit]' are the only reserved variable strings. 
	This means these string variable will change on textarea input. 
	
###indicatorUpdateSpeed (number)
    The delay placed on the indicator before update. 
	This is a number that is represented in milliseconds.

###existingIndicator (object)
    Supply an indicator element that is already on the page. 
    Has to be a jQuery object ex. $('#my-textarea-indicator')

###generateIndicator (boolean)
    By default, the indicator will be set to true for auto generation. 
	
###showIndicator (boolean)	
    The indicator will be shown, unless this is set to false.
	
###useNativeMaxlength (boolean)	
    Uses the browser's maxlength if supported when set to true. 
	  However, setting this to false will use the plugin's implementation of maxlength. 
	  Should only set to false for testing purposes.
	
###textLengthChange (boolean)
    Triggers an event when set to true. 
    Allows for a callback when there is a change in the text length of the textarea. 
    This is useful for doing something that this plugin does not provide.

###useMaxlength (boolean)
    When set to false, maxlength is not implemented on the textarea. 
    If the indicator is still present, the plugin will act as character counter. 
    Set 'useMaxlength' to false to allow for overflow.
   
###beforeAttachment (function)   
    Accepts and runs the function that is provided before the plugin initializes (attaching events).
   
###afterAttachment (function)   
    Similar to beforeAttachment but runs the function after plugin initializes.

###existingText (string)    
    Could be 'clear' or 'truncate'. Determines how existing text is treated during initialization.
    The 'clear' option will clear out any existing text during initialization,
    while the 'truncate' option will truncate any excess characters to the right based on the limit.

Notes:
------
Tested on Opera >= 12.10, Ie >= 6 but 7, Firefox 22, Chrome 28 with jQuery 1.10.2. 

Attention has been made to the plugin to implement a maxlength that replicates the default maxlength behavior in a textarea. Text overflow is only allowed when setting the plugin's 'useMaxlength' to false. The plugin by default, constrains text and peforms text truncation. Simple substring operations are not done. The maxlength follows the maxlength that are in present in recent versions of Firefox, Ie, and Chrome.

This plugin will use the browser's native maxlength and will fallback to the plugin's implementation when lacking maxlength support or with only partial maxlength support.

Since Ie9 has faulty support for maxlength, this plugin will monitor the textarea for exceeding the limit and impose the plugins's maxlength. However, this will also override Opera 12 maxlength implementation.

To normalize behaviors of browsers, the textarea is cleared upon page refresh and tab key press is prevented when text is highlighted with the mouse. 

This plugin is mainly a theoretical exercise because most of the newer major browsers do have full support for maxlength (IE >= 10, Firefox >= 4, Opera >= 11, Chrome >= 6). Older browsers version that do not support maxlength in textarea, tend not to be supported by Jquery. The existence of this plugin is then intended to be of use with Ie 6-9.

Information of form support from [here](http://www.wufoo.com/html5/).

Since browsers do not have an undo or redo event in textareas, this plugin will not support undos or redos in the textarea when using the plugin's implementation of maxlength.

ChangeLog:
------
v1.14.0
* Add: Preserve text option on plugin initialization
* Refactor: Thrown errors, Key check

v1.13.3
* Refactor: Namespace events

v1.13.2
* Fix: Update minified source

v1.13.1
* Fix: Readme demo link update

v1.13.0
* Add: Punctuation support for the string variables
* Add: Update speed adjustment for indicator
* Add: Option to turn on or off maxlength
* Add: beforeAttachment and afterAttachment callbacks
* Fix: Better check for limit value
* Fix: Refactoring

v1.12.2
* Fix: Attempt fix url

v1.12.1
* Fix: Touchup readme
* Fix: Proper commit

v1.12.0
* Add: Full support for Ie9.
* Add: Allow multiple countup, countdown, and limit
* Add: Choose to use native maxlength or plugin's maxlength
* Fix: Highlighting and pasting of text when exceeding limit in Ie6-8 causes errors
* Fix: Highlighting with ctrl shift and arrows keys would not allow paste

v1.11
* Add: Full support for Ie6 and Ie8

v1.10
* Add: Use native maxlength when supported
* Add: Ability to show or hide indicator
* Add: Drag and drop restriction

