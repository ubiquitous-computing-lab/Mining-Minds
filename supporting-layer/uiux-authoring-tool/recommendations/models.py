from django.db import models

# Create your models here.

class RecommendationsModel(models.Model):
    title = models.CharField(max_length=255, blank=True)
    recommedationt_text = models.CharField(max_length=255, blank=True)
    timeStamp = models.CharField(max_length=255, blank=True)
    url=models.CharField(max_length=255, blank=True)

    def __str__(self):
        return self.title





