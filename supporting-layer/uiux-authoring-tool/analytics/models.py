"""
 # UI/UX Authoring Tool
 # @license http://www.apache.org/licenses/LICENSE-2.0
 # Author @ Jamil Hussain
"""

from django.db import models
from accounts.models import MyUser
from django.utils import timezone
from django_countries.fields import CountryField

#APP must be create for the collection of Obeservational Log using UI/UX Analytics SDK.
class App(models.Model):
    app_name = models.CharField(max_length=50)

    def __str__(self):
        return self.app_name

class ActionLog(models.Model):
    action_name = models.CharField(max_length=100)

#Main Oberventional log Model that will store the Disptached user action log by the  UI/UX Analytics SDK Dispatcher
class Log(models.Model):
    actionlog = models.ForeignKey(ActionLog, related_name='logs', on_delete=models.CASCADE)
    app = models.ForeignKey(App, on_delete=models.CASCADE)
    appuser = models.ForeignKey(MyUser, on_delete=models.CASCADE)
    new_visit=models.IntegerField(blank=True,null=True)
    action_name = models.CharField(max_length=50, blank=True)
    entry_screen = models.CharField(max_length=50, blank=True)
    exit_screen = models.CharField(max_length=50, blank=True)
    visit_time = models.DateTimeField(blank=True,null=True)
    first_visit_timestamp = models.BigIntegerField(blank=True,null=True)
    prevoius_visit_timestamp = models.BigIntegerField(blank=True,null=True)
    screen_resolution = models.CharField(max_length=70, blank=True)
    user_agent = models.CharField(max_length=70, blank=True)
    language = models.CharField(max_length=7, blank=True)
    country = models.CharField(max_length=50, blank=True)
    event_category = models.CharField(max_length=70, blank=True)
    event_name = models.CharField(max_length=70, blank=True)
    event_action = models.CharField(max_length=70, blank=True)
    event_value = models.CharField(max_length=70, blank=True)
    total_events = models.IntegerField(blank=True,null=True)
    total_number_of_visit = models.IntegerField(blank=True,null=True)


    def __unicode__(self):
        return '%d: %s' % (self.action_name, self.entry_screen)


