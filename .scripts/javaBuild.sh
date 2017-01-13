find . -iname *.java > files.txt
javac -cp $HOME/libpackage/* @files.txt
