
currentFolder=$PWD

# Byrja a ad setja upp allt fyrir serverinn og initialize-a SQL
cd server
bash install.sh
cd $currentFolder

# Installar ollu fyrir API
cd API
bash install.sh
mvn package
cd $currentFolder

# Test keyra serverinn, endilega keyra sidan 'mvn package' 
cd server/initial
./gradlew bootRun

