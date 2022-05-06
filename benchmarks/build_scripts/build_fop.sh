cd ..
find build_src/fop -type f -name '*.class' -exec rm {} +
ant fop-jar
