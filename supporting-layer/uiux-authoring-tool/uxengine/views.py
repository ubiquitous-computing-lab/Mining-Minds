from django.shortcuts import render
from django.http import HttpResponseRedirect, Http404
from analytics.models import App

# Create your views here.
def homeView(request):
    Apps = App.objects.all().count();
    context={
        "app_name": Apps,
    }
    if not request.user.is_authenticated:
        return HttpResponseRedirect("/accounts/login")
    else:
        return render(request, "home.html", context)