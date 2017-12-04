from django.conf.urls import url,include
from django.contrib import admin
from accounts.api import views
from .views import (
    UserCreateAPIView,
    UserLoginAPIView,
    UserListAPIview
    )


urlpatterns = [
    url(r'^list/', UserListAPIview.as_view(), name='list' ),
    url(r'^login/$', UserLoginAPIView.as_view(), name='login'),
    url(r'^register/$', UserCreateAPIView.as_view(), name='register'),

]
