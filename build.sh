build-1_16_5(){
	cd 1_16_5
	bash build.sh
	cd ..
	mv 1_16_5/packages/* packages/
}
build(){
	mkdir packages
	build-1_16_5
}
build
