# Generated by Django 2.2 on 2019-05-07 18:35

import django.db.models.deletion
from django.db import migrations, models


class Migration(migrations.Migration):
    dependencies = [
        ('api', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Task',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('description', models.CharField(max_length=256)),
                ('completed', models.BooleanField(default=False)),
                ('person', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='api.Person')),
                ('race', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='api.Race')),
            ],
        ),
    ]
