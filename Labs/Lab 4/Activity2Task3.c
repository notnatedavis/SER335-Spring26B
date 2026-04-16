/* Labs/Lab 4/Task3.c
 * 
 * Task 3 Code Snippet
 * SEI CERT Guideline: INT30-C
 * Ensure that unsigned integer operations do not wrap
 */

#include <stdio.h>
#include <limits.h>
#include <stdlib.h>

/*
 * safely adds two unsigned integers
 */
int safe_add(unsigned int x, unsigned int y, unsigned int *result) {
    // precondition test : 
    //    if x > UINT_MAX - y ; overflow will occur
    if (x > UINT_MAX - y) {
        return -1; // indicate overflow
    }
    *result = x + y;
    return 0;
}

void process(unsigned int x, unsigned int y) {
    unsigned int sum;
    if (safe_add(x, y, &sum) != 0) {
        fprintf(stderr, "Error: unsigned integer overflow detected\n");
        exit(EXIT_FAILURE);
    }
    printf("Sum of %u and %u: %u\n", x, y, sum);
}

int main() {
    unsigned int a = 40918;
    unsigned int b = 21293;
    process(a, b);
    return 0;
}