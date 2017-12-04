"""
 # UI/UX Authoring Tool
 # @license http://www.apache.org/licenses/LICENSE-2.0
 # Author @ Jamil Hussain
"""

from django.shortcuts import render
from .forms import AppCreationForm
from .models import (App,ActionLog,Log)
from accounts.models import MyUser
from django.http import HttpResponseRedirect, Http404
from django.db.models import Count, Min, Sum, Avg
from django.utils import timezone


def createApp(request, *args, **kwargs):
    form = AppCreationForm(request.POST or None)
    if form.is_valid():
        form.save()
        return HttpResponseRedirect("analytics/overview.html")
    return render(request, "analytics/app.html", {"form": form})

def homeView(request):
    application_list=App.objects.all()
    context = {
            "app_list": application_list
      }

    if not request.user.is_authenticated:
        return HttpResponseRedirect("/accounts/login")
    else:
        return render(request, 'analytics/home.html',context)


def overviewView(request):
    actions_logs=ActionLog.objects.all()
    logs=Log.objects.filter(visit_time__gt=timezone.now()).exclude(event_category__isnull=True).exclude(event_category__exact='').values('visit_time','event_category','event_name','event_action')
    #gd_total= Log.objects.annotate(total_country=Sum('Log__country'))
    gb_list= Log.objects.values('country').annotate(Count('country'))
    # visits=Log.objects.extra({'visit_time' : "date(visit_time)"}).values('visit_time').annotate(Count('visit_time'))
    device_model=Log.objects.exclude(user_agent__isnull=True).exclude(user_agent__exact='').values('user_agent').distinct()

    andorid=Log.objects.exclude(user_agent__isnull=True).exclude(user_agent__exact='').filter(user_agent__contains='Android').values('user_agent').count()
    other = Log.objects.exclude(user_agent__isnull=True).exclude(user_agent__exact='').exclude(user_agent__contains='Android').values('user_agent').count()
    total_visit= Log.objects.all().count()
    visits= Log.objects.extra({'vists_date': "date(visit_time)"}).values('vists_date').annotate(count=Count('id'))
    user=MyUser.objects.all()
    male = MyUser.objects.filter(gender='male')
    female = MyUser.objects.filter(gender='female')
    context = {
        'actionlog':actions_logs,
        'log': logs,
        'gb_total': gb_list,
        'user': user,
        'male': male,
        'female': female,
        'visits' : visits,
        'total_visit': total_visit,
        'device_model': device_model,
        'andorid' : andorid,
        'other' : other
    }
    return render(request, 'analytics/overview.html', context)


def screensView(request):
    context = {}
    return render(request, 'analytics/screens.html', context)

def eventsView(request):
    context = {}
    return render(request, 'analytics/events.html', context)

def locationsView(request):
    context = {}
    return render(request, 'analytics/locations.html', context)

def exceptionsView(request):
    context = {}
    return render(request, 'analytics/exceptions.html', context)

