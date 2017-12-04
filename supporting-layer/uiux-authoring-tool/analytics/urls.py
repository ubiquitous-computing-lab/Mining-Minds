"""
 # UI/UX Authoring Tool
 # @license http://www.apache.org/licenses/LICENSE-2.0
 # Author @ Jamil Hussain
"""
from django.conf.urls import url, include
from django.contrib import admin
from . import views


urlpatterns = [
        url(r'^$',views.homeView, name='home'),
        url(r'^overview',views.overviewView, name='overview'),
        url(r'^createapp',views.createApp, name='createapp'),
        url(r'^locations',views.locationsView, name='location'),
        url(r'^screens',views.screensView, name='screens'),
        url(r'^events',views.eventsView, name='event'),
        url(r'^exceptions',views.exceptionsView, name='exceptions'),
]

