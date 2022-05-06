cd ..
find ./build_src/pmd -type f -name '*.class' -exec rm {} +
ant pmd-jar
