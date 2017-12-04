from django.conf.urls import url,include
from django.contrib import admin
from accounts.api import views
from .views import RecommendationListAPIview


urlpatterns = [
      url(r'^$', RecommendationListAPIview.as_view(), name='recommendations'),

]
