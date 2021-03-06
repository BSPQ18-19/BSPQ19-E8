# Generated by Django 2.2 on 2019-04-27 17:12

import django.db.models.deletion
from django.conf import settings
from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.CreateModel(
            name='Person',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('personal_id', models.CharField(max_length=32, unique=True)),
                ('birth_date', models.DateField()),
                (
                'user', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
        migrations.CreateModel(
            name='Race',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('edition', models.CharField(max_length=32, unique=True)),
                ('sponsor', models.CharField(blank=True, max_length=32)),
                ('place', models.CharField(max_length=32)),
                ('time', models.DateTimeField()),
                ('prize', models.IntegerField()),
                ('price', models.IntegerField()),
                ('helpers', models.ManyToManyField(blank=True, related_name='helper', to='api.Person')),
                ('organizer', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='organizer',
                                                to='api.Person')),
            ],
        ),
        migrations.CreateModel(
            name='Runner',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('number', models.IntegerField()),
                ('person', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='api.Person')),
                ('race', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='api.Race')),
            ],
        ),
        migrations.AddField(
            model_name='race',
            name='runners',
            field=models.ManyToManyField(blank=True, through='api.Runner', to='api.Person'),
        ),
    ]
