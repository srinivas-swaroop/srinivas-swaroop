#!/bin/bash
# Program to swap two numbers using functions

swap() {
    temp=$1
    a=$2
    b=$temp
    echo "After swapping: a = $a, b = $b"
}

echo "Enter two numbers:"
read a b

echo "Before swapping: a = $a, b = $b"

# Call the function
swap $a $b
