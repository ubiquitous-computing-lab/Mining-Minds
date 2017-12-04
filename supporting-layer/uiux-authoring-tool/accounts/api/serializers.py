from django.contrib.contenttypes.models import ContentType
from django.contrib.auth import get_user_model
from accounts.models import MyUser
from django.db.models import Q

from rest_framework.serializers import (
    CharField,
    EmailField,
    HyperlinkedIdentityField,
    ModelSerializer,
    SerializerMethodField,
    ValidationError
    )


class UserListSerializer(ModelSerializer):
    class Meta:
        model=MyUser
        fields =('username','email','password', 'date_of_birth', 'gender','height','weight')

class UserDetailSerializer(ModelSerializer):
    class Meta:
        model = MyUser
        fields = [
            'username',
            'email',
            'first_name',
            'last_name',
        ]

class UserCreateSerializer(ModelSerializer):
    email = EmailField(label='Email Address')
    email2 = EmailField(label='Confirm Email')
    class Meta:
        model = MyUser
        fields = [
            'username',
            'email',
            'email2',
            'password',
            'date_of_birth',
            'gender',
            'height',
            'weight',
            'user_sight',
            'user_hearing',
            'user_touch'
            
        ]
        extra_kwargs = {"password":
                            {"write_only": True}
                            }
    def validate(self, data):
        email = data['email']
        user_qs = MyUser.objects.filter(email=email)
        if user_qs.exists():
            raise ValidationError("This user has already registered.")
        return data


    def validate_email(self, value):
        data = self.get_initial()
        email1 = data.get("email2")
        email2 = value
        if email1 != email2:
            raise ValidationError("Emails must match.")
        
        user_qs = MyUser.objects.filter(email=email2)
        if user_qs.exists():
            raise ValidationError("This user has already registered.")

        return value

    def validate_email2(self, value):
        data = self.get_initial()
        email1 = data.get("email")
        email2 = value
        if email1 != email2:
            raise ValidationError("Emails must match.")
        return value



    def create(self, validated_data):
        username = validated_data['username']
        email = validated_data['email']
        password = validated_data['password']
        user_obj = MyUser(
                username = username,
                email = email
            )
        user_obj.set_password(password)
        user_obj.save()
        return validated_data

class UserLoginSerializer(ModelSerializer):
    password=CharField()
    email = EmailField(label='Email Address')
    gender=CharField(read_only=True)

    class Meta:
        model = MyUser
        fields = [
            'email',
            'password',
            'gender',
        ]

        extra_kwargs = {"password":
                            {"write_only": True}
                        }

    def validate(self, data):
        email = data['email']
        password=data['password']
        user_qs_final = MyUser.objects.filter( Q(email__iexact=email)).distinct()
        if not user_qs_final.exists() and user_qs_final.count() != 1:
            raise ValidationError("Invalid credentials -- user not exist")
        user_obj = user_qs_final.first()
        if not user_obj.check_password(password):
            raise ValidationError("Invalid credentials -- passowrd invalid")
        return data



