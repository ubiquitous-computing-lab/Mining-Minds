"""
 # UI/UX Authoring Tool
 # @license http://www.apache.org/licenses/LICENSE-2.0
 # Author @ Jamil Hussain
"""

from django.contrib import admin

from .models import ( App, ActionLog, Log)

admin.site.register(App)
admin.site.register(Log)
admin.site.register(ActionLog)
