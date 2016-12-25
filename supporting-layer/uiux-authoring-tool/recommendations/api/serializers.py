from recommendations.models import RecommendationsModel
from rest_framework import serializers


class RecommendationSerializer(serializers.ModelSerializer):
    class Meta:
        model = RecommendationsModel
        fields = ('title','recommedationt_text','url')



