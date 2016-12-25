from rest_framework.views import APIView
from .serializers import RecommendationSerializer
from recommendations.models import RecommendationsModel

from rest_framework.mixins import DestroyModelMixin, UpdateModelMixin
from rest_framework.generics import ListAPIView

class RecommendationListAPIview(ListAPIView):
    queryset = RecommendationsModel.objects.all()
    serializer_class = RecommendationSerializer
