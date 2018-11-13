
# Fyrir log-in sem virkar alltaf nota:

## Username: admin

## Password: admin



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

# API 

Basically drasl sem generate-ar database. Verdur sidan mjog hentugt a naestu
onn thegar vid forum ad gera Android, thad er nebbla haegt ad importa thessu
sem pakka sem ser um ad tala vid database-inn med requests a IP tolu.
