all: linux-x86 linux-x86_64 windows-x86_64 mac-x86_64
compile: gradlew
	./gradlew clean build javaToolchains buildKatoPlugin
	rm -rf packages
	mkdir -p packages
	mv build/kato/*.jar packages/
	mv packages/*.jar packages/farbic-all.jar
extract: compile
	rm -rf extract
	mkdir -p extract
	unzip packages/farbic-all.jar -d extract/ > /dev/null
clean:
	rm -rf target extract
linux-x86_64: target := target/linux-x86_64
linux-x86_64: name := farbic-linux-x86_64.jar
linux-x86_64: extract
	mkdir -p $(target)/extract $(target)/tmp
	cp -r extract $(target)
	# rocksdb
	mv $(target)/extract/librocksdbjni-linux64.so $(target)/tmp
	rm $(target)/extract/librocksdbjni*
	mv $(target)/tmp/librocksdbjni-linux64.so $(target)/extract
	# package
	jar -c -f packages/$(name) -C $(target)/extract/ .
	rm -rf $(target)
linux-x86: target := target/linux-x86
linux-x86: name := farbic-linux-x86.jar
linux-x86: extract
	mkdir -p $(target)/extract $(target)/tmp
	cp -r extract $(target)
	# rocksdb
	mv $(target)/extract/librocksdbjni-linux32.so $(target)/tmp
	rm $(target)/extract/librocksdbjni*
	mv $(target)/tmp/librocksdbjni-linux32*.so $(target)/extract
	# package
	jar -c -f packages/$(name) -C $(target)/extract/ .
	rm -rf $(target)
windows-x86_64: target := target/windows-x86_64
windows-x86_64: name := farbic-windows-x86_64.jar
windows-x86_64: extract
	mkdir -p $(target)/extract $(target)/tmp
	cp -r extract $(target)
	# rocksdb
	mv $(target)/extract/librocksdbjni-win64.dll $(target)/tmp
	rm $(target)/extract/librocksdbjni*
	mv $(target)/tmp/librocksdbjni-win64.dll $(target)/extract
	# package
	jar -c -f packages/$(name) -C $(target)/extract/ .
	rm -rf $(target)
mac-x86_64: target := target/mac-x86_64
mac-x86_64: name := farbic-mac-x86_64.jar
mac-x86_64: extract
	mkdir -p $(target)/extract $(target)/tmp
	cp -r extract $(target)
	# rocksdb
	mv $(target)/extract/librocksdbjni-osx.jnilib $(target)/tmp
	rm $(target)/extract/librocksdbjni*
	mv $(target)/tmp/librocksdbjni-osx.jnilib $(target)/extract
	# package
	jar -c -f packages/$(name) -C $(target)/extract/ .
	rm -rf $(target)


