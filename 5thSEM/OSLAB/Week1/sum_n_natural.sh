#!/bin/bash
# Program to find the sum of n natural numbers using for loop

n=12
sum=0

for (( i=1; i<=n; i++ ))
do
    sum=$((sum + i))
done

echo "The sum of first $n natural numbers is: $sum"
