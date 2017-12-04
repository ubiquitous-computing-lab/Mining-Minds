from django.conf.urls import url, include
from django.contrib import admin
from . import views

urlpatterns = [

    url(r'^$', views.homeView, name='home'),
    url(r'^api/', include("analytics.api.urls", namespace='analytics-api')),
    url(r'^api/users/', include("accounts.api.urls", namespace='users-api')),
    url(r'^api/recommendations/', include("recommendations.api.urls", namespace='recommendation-api')),
    url(r'^admin/', admin.site.urls),
    url(r'^analytics/', include('analytics.urls')),
    url(r'^accounts/', include('accounts.urls')),
]

