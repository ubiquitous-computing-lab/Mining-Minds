from analytics.models import (Log, ActionLog)
from rest_framework import serializers


class LogSerializer(serializers.ModelSerializer):
    class Meta:
        model = Log
        fields = ('app','appuser','country','screen_resolution','user_agent','action_name', 'entry_screen', 'exit_screen','visit_time', 'first_visit_timestamp' ,'prevoius_visit_timestamp','language', 'event_action','event_category','event_name','event_value')

class ActionLogSerializer(serializers.ModelSerializer):
    logs = LogSerializer(many=True)

    action_name = serializers.HiddenField(default="Request")

    class Meta:
        model = ActionLog
        fields = ('action_name', 'logs')

    def create(self, validated_data):
        logs_data = validated_data.pop('logs')
        actionlog = ActionLog.objects.create(**validated_data)
        for logs_data in logs_data:
            Log.objects.create(actionlog=actionlog, **logs_data)
        return actionlog

