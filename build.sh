build-fabric(){
  ./gradlew clean :fabric-1_16:remapJar
  mv fabric-1_16/build/libs/*.jar packages/fabric-1_16.jar
  ./gradlew clean :fabric-1_17:remapJar
  mv fabric-1_17/build/libs/*.jar packages/fabric-1_17.jar
  ./gradlew clean :fabric-1_18:remapJar
  mv fabric-1_18/build/libs/*.jar packages/fabric-1_18.jar
  ./gradlew clean :fabric-1_19:remapJar
  mv fabric-1_19/build/libs/*.jar packages/fabric-1_19.jar
}
build-forge(){
  ./gradlew clean :forge-1_18:remapJar
  mv forge-1_18/build/libs/*.jar packages/forge-1_18.jar
}
build(){
  rm -rf packages
  mkdir packages
  ./gradlew clean
  build-fabric
  build-forge
}
build
