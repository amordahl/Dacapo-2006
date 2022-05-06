cd ..
find ./build_src/bloat -type f -name '*.class' -exec rm {} +
ant bloat-jar
