cd ..
find ./build_src/hsqldb -type f -name '*.class' -exec rm {} +
ant hsqldb-jar
