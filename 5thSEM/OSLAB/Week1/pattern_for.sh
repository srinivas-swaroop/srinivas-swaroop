#!/bin/bash
# Program to display pattern using for loop

s='*'




for(( j=5;j>=0;j-- ))
do
    for(( i=1;i<=j;i++ ))
    do
        echo -n "$s"
    done
    echo

done