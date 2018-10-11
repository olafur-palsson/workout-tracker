# Naesta verkefni

## Gera tvo requirements vel.

Gaejinn sagdi ad vid verdum ad utlista requirements vel. Thad a ekki ad vera neitt
UML en hins vegar vill hann definitely:

1. Nakvaema lysingu a hvada data vantar / er sent.

2. Look-and-feel lysingu

En samt sem adur lagdi hann mjog mikla aherslu ad thetta se abstract-detailed lysing
thvi hvernig thetta virkar.


---

# Hugmyndir ur fyrsta tima


## Design

#### One tap -> one action everything

#### Social network



## Must have

#### Skra aefingar

#### (Personalised med access)

#### Skra rutinur



## Actually usable

#### Generate-a rutinu




## Want to have

#### Aldrei hitta sjukro?

#### Boka tima hja sjukro

#### Gera aefingar rett

#### Sjukrathjalfara access

#### Prevention aefingar (physio verified)

#### Sja rutinur hja odrum

#### Social Feed

#### Social Follow

#### Popular on the app

#### Public/Private profile

#### Location based noto sem segir ther ad skra aefingu

#### Time based notification based on regular workout times

#### Skra weights available

---

# Quick start


### Minimum install listinn

Node

MySQL

JDK -----> 8! <------ (java --version)

### Restin

(Windows) Ubuntu windows app (mest safe)

Gradle

Maven

(Ubuntu) 

npm install (coming soon)

---

# Docs

## Um install

Google er betri ad utskyra en eg.

## Commands

---

#### . (root dir)

`npm install`
  
  Setja upp allt front-end

`npm start`

  Keyrir upp dev server

---

#### ./server/initial/

`./gradlew build`   

  Nota gradle wrapper til ad byggja serverinn 

`./gradlew bootRun`   

  Nota gradle wrapper til ad byggja OG keyra serverinn 

---

# Filesystem

    ./                          web-app home dir
      server/                   spring server home dir
             gradle/            --  auto  --
             test/              --  auto  --
             setup/             install files
             initial/           --  auto fyrir utan... --
                     src/       *Source code*
      API/                      API/test home dir
          jar/classname.jar     output af 'mvn package'
          src/                  *Source code*
          pom.xml               setup file
                 

              
---

# Todo listinn

## API build tool

1. Bua til eitthvad shellscript sem auto-buildar nyjan klasa 

2. Og sidan update API sem smidar API-inn og byr til test
