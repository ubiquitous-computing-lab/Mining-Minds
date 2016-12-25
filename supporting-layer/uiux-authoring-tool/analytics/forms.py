"""
 # UI/UX Authoring Tool
 # @license http://www.apache.org/licenses/LICENSE-2.0
 # Author @ Jamil Hussain
"""

from django import forms
from .models import App


class AppCreationForm(forms.ModelForm):
    #A form for creating new APP. Includes all the requiredfields
    app_name = forms.CharField()
    class Meta:
        model = App
        fields = ('app_name','id')

    def save(self, commit=True):
        app = super(AppCreationForm, self).save(commit=False)
        if commit:
            app.save()
        return app





