#!/bin/bash
# Program to display pattern using while loop

i=5
while [ $i -ge 1 ]
do
    j=$i
    while [ $j -ge 1 ]
    do
        echo -n "$j "
        j=$((j - 1))
    done

    k=$i
    while [ $k -le 5 ]
    do
        echo -n "* "
        k=$((k + 1))
    done

    echo
    i=$((i - 1))
done
