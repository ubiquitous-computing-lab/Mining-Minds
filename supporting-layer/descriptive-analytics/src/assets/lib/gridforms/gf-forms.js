(function($) {
  'use strict';

  var namespace = 'gf',
    GridForms = {
      el: {
        fieldsRows: $('[class^="'+namespace+'-row-"], [class*=" '+namespace+'-row-"]'),
        fieldsContainers: $('[class^="'+namespace+'-col-"], [class*=" '+namespace+'-col-"]'),
        focusableFields: $('input[type="text"], textarea, select'),
        window: $(window)
      },
      init: function() {
        this.focusField(this.el.focusableFields.filter(':focus'));
        this.equalizeFieldHeights();
        this.events();
      },
      focusField: function(currentField) {
        currentField
          .closest('[class^="'+namespace+'-col-"], [class*=" '+namespace+'-col-"]')
          .addClass(namespace+'-focus');
      },
      removeFieldFocus: function() {
        this.el.fieldsContainers.removeClass(namespace+'-focus');
      },
      events: function() {
        var that = this;
        that.el.fieldsContainers.click(function() {
          $(this).find('input, textarea, select').focus();
        });
        that.el.focusableFields.focus(function() {
          that.focusField($(this));
        });
        that.el.focusableFields.blur(function() {
          that.removeFieldFocus();
        });
        that.el.window.resize(function() {
          that.equalizeFieldHeights();
        });

      },
      equalizeFieldHeights: function() {
        this.el.fieldsContainers.css("height", "auto");

        var fieldsRows = this.el.fieldsRows;
        var fieldsContainers = this.el.fieldsContainers;

        // Make sure that the fields aren't stacked
        if (!this.areFieldsStacked()) {
          fieldsRows.each(function() {
            // Get the height of the row (thus the tallest element's height)
            var fieldRow = $(this);
            var rowHeight = fieldRow.css('height');

            // Set the height for each field in the row...
            fieldRow.find(fieldsContainers).css('height', rowHeight);
          });
        }
      },
      areFieldsStacked: function() {
        // Get the first row
        // which does not only contain one field
        var firstRow = this.el.fieldsRows
          .not('.'+namespace+'-row-1')
          .first();

        // Get to the total width
        // of each field witin the row
        var totalWidth = 0;
        firstRow.children().each(function() {
          totalWidth += $(this).width();
        });

        // Determine whether fields are stacked or not
        return firstRow.width() <= totalWidth;
      }
    };
  GridForms.init();
}(jQuery));