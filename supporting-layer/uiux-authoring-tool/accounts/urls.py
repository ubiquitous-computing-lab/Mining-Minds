
from django.conf.urls import url
from django.contrib import admin


from .views import activate_user_view, home, register, login_view, logout_view

urlpatterns = [
    url(r'^$', home),
    url(r'^register/$', register),
    url(r'^login/$', login_view),
    url(r'^logout/$', logout_view),
    url(r'^activate/(?P<code>[a-z0-9].*)/$', activate_user_view),
]
