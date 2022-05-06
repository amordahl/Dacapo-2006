cd ..
find ./build_src/chart -type f -name '*.class' -exec rm {} +
ant chart-jar
