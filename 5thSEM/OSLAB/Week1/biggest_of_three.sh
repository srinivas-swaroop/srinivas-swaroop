echo "Saving Three Numbers"

num1=10;
num2=5;
num3=7;

if [ $num1>$num2 ] && [ $num1>$num3 ]
then
    echo "num1 larger"
elif [ $num2>$num1 ] && [ $num2>$num3 ]
then 
    echo "num2 larger ie $num2"
else
    echo "num3 larger"
fi
