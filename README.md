# Hair Salon Site

##### Epicodus review using Java and Postgres, 02.25.2016

##### Megan Fayer

## Description
This site allows a user to enter the name of stylists working at their hair salon. The stylist can then add their clients names, phone number, and email. Clients can be updated or deleted and stylists can also be deleted.

## Setup

Clone this repository:
```
$ cd ~/Desktop
$ git clone https://github.com/buffbabyfinn/HairSalonJavaReview
$ cd HairSalonJavaReview
```

Open terminal and run Postgres (Mac only, Windows must run Postgres manually):
```
$ postgres
```

Open a new tab ('ctrl' + n) in terminal and create the `hair_salon` database:
```
$ psql
$ CREATE DATABASE hair_salon;
$ psql hair_salon < hair_salon.sql
```

Navigate back to the directory where this repository has been cloned and run gradle:
```
$ gradle run
```
Feel free to import the `hair_salon_test` database as well as a template for testing.

## Legal

Copyright (c) 2016 Megan Fayer

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
