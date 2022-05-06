cd ..
find ./build_src/jython -type f -name '*.class' -exec rm {} +
ant jython-jar
