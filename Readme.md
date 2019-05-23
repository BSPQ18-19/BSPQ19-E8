# RaceOrganizer [![Build Status](https://travis-ci.org/BSPQ18-19/BSPQ19-E8.svg?branch=master)](https://travis-ci.org/BSPQ18-19/BSPQ19-E8)
Student project for Software Process and Quality course at Deusto University 

Current Dev Build: [![Build Status](https://travis-ci.org/BSPQ18-19/BSPQ19-E8.svg?branch=dev)](https://travis-ci.org/BSPQ18-19/BSPQ19-E8)

Members:
- [Rafael Romón](https://github.com/rafaelromon)
- [Andrea Gallego](https://github.com/andreagb35)
- [Asier Salazar](https://github.com/asiers49)
- [Iván Sánchez](https://github.com/ivykoko1)

## Description:
RaceOrganizer is a platform that allows you to discover new races and signup to them, keeping you up to date on 
everything you need to know, allowing you to just focus on getting the best results.

As an event organizer, RaceOrganizer enables to simplify recruiting helpers for your race and managing the tasks
 needed for your race to go smoothly.
 
## Documentation

- The documentation for the client was generated using Doxygen, and is available at [Doxygen](/index.html>Doxygen<).

- Documentation for the API follows the OpenAPI standard and can be found at [SwaggerHub](https://app.swaggerhub.com/apis-docs/rafaelromon/RaceOrganizer/1.0.0#/).

## Architecture
RaceOrganizer uses a simply REST client-server architecture, with a server on Django and a client written in Java.

RaceOrganizer uses Django's MariaDB integration in order to store the information using the model in the following 
diagram:

<p align="center">
  <img src="https://github.com/BSPQ18-19/BSPQ19-E8/blob/master/.web/ClassDiagram.png" alt="classDiagram"/>
</p>

## License
This project falls under the GPL 3 license, feel free to use it, modify it and maybe notify us know of your changes :)

