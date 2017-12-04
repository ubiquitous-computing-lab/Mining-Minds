from django.conf.urls import url, include
from django.contrib import admin

from analytics.api import views

from rest_framework.routers import DefaultRouter

router = DefaultRouter()
router.register(r'actionlog', views.ActionLogViewSet)

urlpatterns = [
        url(r'^analytics/', include(router.urls)),
]

