build-1_16(){
	cd 1_16
	bash build.sh
	cd ..
	mv 1_16/packages/* packages/
}
build-1_17(){
	cd 1_17
	bash build.sh
	cd ..
	mv 1_17/packages/* packages/
}
build-1_18(){
	cd 1_18
	bash build.sh
	cd ..
	mv 1_18/packages/* packages/
}
build(){
	mkdir packages
	./gradlew clean :1_16:remapJar
	build-1_16
	./gradlew clean :1_17:remapJar
	build-1_17
	./gradlew clean :1_18:remapJar
	build-1_18
}
build
