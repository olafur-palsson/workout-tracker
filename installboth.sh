
currentFolder=$PWD

cd server
bash install.sh
cd $currentFolder

cd API
bash install.sh
cd $currentFolder



cd server/initial
./gradlew bootRun

