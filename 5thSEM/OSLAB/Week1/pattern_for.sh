#!/bin/bash
# Program to display pattern using for loop

for (( i=5; i>=1; i-- ))
do
    # Print decreasing numbers
    for (( j=i; j>=1; j-- ))
    do
        echo -n "$j "
    done

    # Print stars
    for (( k=i; k<=5; k++ ))
    do
        echo -n "* "
    done

    echo
done
