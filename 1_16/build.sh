build(){
	rm -rf packages
	mkdir -p packages
	rm build/libs/*-dev.jar
	mv build/libs/*.jar packages/
	mv packages/*.jar packages/fabric-1_16.jar
}
build
