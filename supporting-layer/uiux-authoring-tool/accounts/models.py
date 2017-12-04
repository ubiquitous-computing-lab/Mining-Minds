"""
 # UI/UX Authoring Tool
 # @license http://www.apache.org/licenses/LICENSE-2.0
 # Author @ Jamil Hussain
"""

from django.conf import settings
from django.contrib.auth.models import (
    BaseUserManager, AbstractBaseUser
)
from django.core.validators import RegexValidator
from django.db import models
from django.db.models.signals import post_save
# Create your models here.
from .utils import code_generator

USERNAME_REGEX = '^[a-zA-Z0-9.+-]*$'

class MyUserManager(BaseUserManager):
    def create_user(self, username, email, password=None):
        """
        Creates and saves a User with the given email, date of
        birth and password.
        """
        if not email:
            raise ValueError('Users must have an email address')

        user = self.model(
            username = username,
            email=self.normalize_email(email),
        )

        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, username, email, password):
        """
        Creates and saves a superuser with the given email, date of
        birth and password.
        """
        user = self.create_user(
            username,
            email,
            password=password,
        )
        user.is_admin = True
        user.is_staff = True
        user.save(using=self._db)
        return user


#The data should be collected on the bases of user ID. This Model store information about the user

class MyUser(AbstractBaseUser):
    username = models.CharField(
                max_length=255, 
                validators=[
                    RegexValidator(
                        regex = USERNAME_REGEX,
                        message = 'Username must be Alpahnumeric or contain any of the following: ". @ + -" ',
                        code='invalid_username'
                    )],
                unique=True,
            )
    email = models.EmailField(
        verbose_name='email address',
        max_length=255,
        unique=True,
    )

    GENDER_CHOICES = (
        ('male', 'Male'),
        ('female', 'Female'),
    )


    password = models.CharField(max_length=255)
    date_of_birth= models.DateField(blank=True,null=True)
    gender= models.CharField(max_length=50, null=True, choices=GENDER_CHOICES)
    height= models.IntegerField(blank=True,null=True)
    weight=models.IntegerField(blank=True,null=True)
    user_sight = models.CharField(max_length=50, null=True)
    user_hearing = models.CharField(max_length=50, null=True)
    user_touch = models.CharField(max_length=50, null=True)

    is_active = models.BooleanField(default=True)
    is_staff = models.BooleanField(default=False)
    is_admin = models.BooleanField(default=False)

    objects = MyUserManager()

    USERNAME_FIELD = 'username'
    REQUIRED_FIELDS = ['email']

    def get_full_name(self):
        # The user is identified by their email address
        return self.email

    def get_short_name(self):
        # The user is identified by their email address
        return self.email

    def __str__(self):              # __unicode__ on Python 2
        return self.email

    def has_perm(self, perm, obj=None):
        "Does the user have a specific permission?"
        # Simplest possible answer: Yes, always
        return True

    def has_module_perms(self, app_label):
        "Does the user have permissions to view the app `app_label`?"
        # Simplest possible answer: Yes, always
        return True




class ActivationProfile(models.Model):
    user    = models.ForeignKey(settings.AUTH_USER_MODEL)
    key     = models.CharField(max_length=120)
    expired = models.BooleanField(default=False)

    def save(self, *args, **kwargs):
        self.key = code_generator()
        super(ActivationProfile, self).save(*args, **kwargs)


def post_save_activation_receiver(sender, instance, created, *args, **kwargs):
    if created:
        #send email
        print('activation created')

post_save.connect(post_save_activation_receiver, sender=ActivationProfile)




class Profile(models.Model):
    user = models.OneToOneField(settings.AUTH_USER_MODEL)
    city = models.CharField(max_length=120, null=True, blank=True)

    def __str__(self):
        return str(self.user.username)

    def __unicode__(self):
        return str(self.user.username)


def post_save_user_model_receiver(sender, instance, created, *args, **kwargs):
    if created:
        try:
            Profile.objects.create(user=instance)
            ActivationProfile.objects.create(user=instance)
        except:
            pass

post_save.connect(post_save_user_model_receiver, sender=settings.AUTH_USER_MODEL)
