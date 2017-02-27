;**********************************************************************
;                                                                     *
;   This file is a basic code template for assembly code generation   *
;   on the PIC16F84A. This file contains the basic code               *
;   building blocks to build upon.                                    *
;                                                                     *
;   Refer to the MPASM User's Guide for additional information on     *
;   features of the assembler (Document DS33014).                     *
;                                                                     *
;   Refer to the respective PIC data sheet for additional             *
;   information on the instruction set.                               *
;                                                                     *
;**********************************************************************
;                                                                     *
;    Filename:        xxx.asm                                         *
;    Date:     		12/8/14                                                       *
;    File Version:                                                    *
;                                                                     *
;    Author:           Coursin Hill                                               *
;    Company:                                                         *
;                                                                     *
;                                                                     *
;**********************************************************************
;                                                                     *
;    Files required: P16F84A.INC                                      *
;                                                                     *
;                                                                     *
;                                                                     *
;**********************************************************************
;                                                                     *
;    Notes:                                                           *
;                                                                     *
;                                                                     *
;                                                                     *
;                                                                     *
;**********************************************************************


    list      p=16F84A             ; list directive to define processor
    #include <p16F84a.inc>         ; processor specific variable definitions

    __CONFIG   _CP_OFF & _WDT_ON & _PWRTE_ON & _RC_OSC

IFSET macro fr,bit,label
	btfss fr,bit ; if (fr.bit) then execute code following macro
	goto label ; else goto label	
      endm

IFCLR macro fr,bit,label
	btfsc fr,bit ; if (! fr.bit) then execute code following macro
	goto label ; else goto label
	  endm

IFEQ macro fr,lit,label
	movlw lit
	xorwf fr,W
	btfss STATUS,Z ; (fr == lit) then execute code following macro
	goto label ; else goto label
	 endm

IFNEQ macro fr,lit,label
	movlw lit
	xorwf fr,W
	btfsc STATUS,Z ; (fr != lit) then execute code following macro
	goto label ; else goto label
	 endm

MOVFF macro from,to
	movf from,W
	movwf to
  	  endm

MOVLF macro lit,fr
	movlw lit
	movwf fr
	  endm

MULT_ENTRY macro label			;macro to check if multiple buttons are pushed
	IFNEQ PORTA, b'01100', label
	IFNEQ PORTA, b'01010', label
	IFNEQ PORTA, b'01001', label
	IFNEQ PORTA, b'00110', label
	IFNEQ PORTA, b'00101', label
	IFNEQ PORTA, b'00011', label
	IFNEQ PORTA, b'01110', label
	IFNEQ PORTA, b'01101', label
	IFNEQ PORTA, b'01011', label
	IFNEQ PORTA, b'00111', label
	IFNEQ PORTA, b'01111', label
	endm

; '__CONFIG' directive is used to embed configuration data within .asm file.
; The lables following the directive are located in the respective .inc file.
; See respective data sheet for additional information on configuration word.

;***** VARIABLE DEFINITIONS
w_temp        EQU     0x0C        ; variable used for context saving 
status_temp   EQU     0x0D        ; variable used for context saving
NEXT_S		  EQU	  0x0E		  ; variable to hold next state
N_S		  	  EQU	  0X0F		  ; variable that holds the next state that isn't SERR or SOK. used to rotate lights without rotate command


S1 equ B'0001'		;state variables
S2 equ B'0010'
S3 equ B'0100'
S4 equ B'1000'
SOK equ B'0000'
SERR equ B'10000000'

;**********************************************************************
RESET_VECTOR      CODE    0x0000  ; processor reset vector
        goto    main             ; go to beginning of program

ISR               CODE    0x0004  ; interrupt vector location

Interrupt:

        movwf  w_temp             ; save off current W register contents
        MOVFF   STATUS, status_temp

;  Place ISR Here
ns:		IFNEQ PORTA, S1, itr	;check to see if any buttons are pushed
		IFNEQ PORTA, S2, itr
		IFNEQ PORTA, S3, itr
		IFNEQ PORTA, S4, itr
		MULT_ENTRY itr
			MOVFF N_S, NEXT_S	;if not, light rotates
itr:
		MOVFF NEXT_S, PORTB		;  if a button is pushed, goes to correct state
		clrf	TMR0			; reset timer
		bcf		INTCON, T0IF	; reset flag
		MOVLF d'60', TMR0		; TMR0 delay to get 1 second program delay

		MOVFF  status_temp, STATUS
		swapf w_temp,f
		swapf w_temp,w
        retfie                    ; return from interrupt

MAIN_PROGRAM    CODE

main:
	clrw W_REG
	bsf STATUS, RP0						
	MOVLF b'01110000', TRISB		;set output
	MOVLF b'11000110', OPTION_REG	;set up timer
	MOVLF b'10100000', INTCON		;set up interrupt
	bcf STATUS, RP0
	MOVLF d'60', TMR0 				;set initial delay of TMR0 for 1 second program delay
	
	
	MOVLF S1, NEXT_S

mloop:
	MOVFF NEXT_S, PORTB		

xs1: IFEQ	PORTB, S1, xs2				;state 1 case
		MOVLF S2, N_S					;state is currently s1, so saves s2
		IFEQ  PORTA, S1, xs1c			;check for correct guess
			MOVLF SOK, NEXT_S			; if guess is correct next state is SOK and move to SOK
			goto xSOK

xs1c:	IFNEQ PORTA, S2, err			;if S1 isnt guessed, check to see if anything else is guessed
		IFNEQ PORTA, S3, err
		IFNEQ PORTA, S4, err
		MULT_ENTRY err
			goto mloop					;if program gets here then there's no input. program loops until interrupt then switches states or until there is an entry and it changes accodingly
	
xs2: IFEQ	PORTB, S2, xs3				;state 2 case
		MOVLF S3, N_S					;state is currently s2, so saves s3
		IFEQ  PORTA, S2, xs2c			;check for correct guess
			MOVLF SOK, NEXT_S			; if guess is correct next state is SOK and move to SOK
			goto xSOK

xs2c:	IFNEQ PORTA, S1, err			;if S2 isnt guessed, check to see if anything else is guessed
		IFNEQ PORTA, S3, err
		IFNEQ PORTA, S4, err
		MULT_ENTRY err
			goto mloop					;if program gets here then there's no input. program loops until interrupt then switches states or until there is an entry and it changes accodingly

xs3: IFEQ	PORTB, S3, xs4				;state 3 case 
		MOVLF S4, N_S					;state is currently s3, so saves s4
		IFEQ  PORTA, S3, xs3c			;check for correct guess
			MOVLF SOK, NEXT_S			; if guess is correct next state is SOK and move to SOK
			goto xSOK	

xs3c:	IFNEQ PORTA, S1, err			;if S3 isnt guessed, check to see if anything else is guessed
		IFNEQ PORTA, S2, err
		IFNEQ PORTA, S4, err
		MULT_ENTRY err
			goto mloop					;if program gets here then there's no input. program loops until interrupt then switches states or until there is an entry and it changes accodingly

xs4: IFEQ	PORTB, S4, xs1				;state 4 case
		MOVLF S1, N_S					;state is currently s4, so saves s1
		IFEQ  PORTA, S4, xs4c			;check for correct guess
			MOVLF SOK, NEXT_S			;if guess is correct next state is SOK and move to SOK
			goto xSOK			

xs4c:	IFNEQ PORTA, S1, err			;if S3 isnt guessed, check to see if anything else is guessed
		IFNEQ PORTA, S2, err
		IFNEQ PORTA, S3, err
		MULT_ENTRY err
			goto mloop					;if program gets here then there's no input. program loops until interrupt then switches states or until there is an entry and it changes accodingly

err:	MOVLF SERR, NEXT_S				;move to error state
		IFNEQ PORTA, S1, err			;stay in serr if buttons still pressed
		IFNEQ PORTA, S2, err
		IFNEQ PORTA, S3, err
		IFNEQ PORTA, S4, err
		MULT_ENTRY err
			MOVLF S4, NEXT_S			;moves to s4 so that when the program 
			goto mloop

xSOK:	MOVLF SOK, NEXT_S				;move to ok state
		IFNEQ PORTA, S1, xSOK			;stay in sok if buttons still pressed
		IFNEQ PORTA, S2, xSOK
		IFNEQ PORTA, S3, xSOK
		IFNEQ PORTA, S4, xSOK
		MULT_ENTRY xSOK
			MOVLF S4, NEXT_S			; move to s4 so when state changes, goes to s1
			goto mloop	
	


        goto $

        END                       ; directive 'end of program'

