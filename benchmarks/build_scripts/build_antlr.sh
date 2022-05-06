cd ..
find ./build_src/antlr/ -type f -name '*.class' -exec rm {} +
ant antlr-jar
