cd ..
find ./build_src/lucene/ -type f -name '*.class' -exec rm {} +
ant luindex-jar
