module turnSignal(input wire right, input wire left, input wire clk, output reg [2:0] leftLight, output reg [2:0] rightLight, output reg [6:0] HEX2);

	
	parameter 	IDLE = 10'b0000000000,
					L1 = 10'b0010000000,
					L1A = 10'b0011000000,
					L1B = 10'b0011100000,
					L2 = 10'b0110000000,
					L2A = 10'b0111000000,
					L2B = 10'b0111100000,
					L3 = 10'b1110000000,
					L3A = 10'b1111000000,
					L3B = 10'b1111100000,
					R1 = 10'b0000000100,
					R1A = 10'b0001000100,
					R1B = 10'b0001100100,
					R2 = 10'b0000000110,
					R2A = 10'b0001000110,
					R2B = 10'b0001100110,
					R3 = 10'b0000000111,
					R3A = 10'b0001000111,
					R3B = 10'b0001100111,
					ERROR = 7'b0000110,
					NOERROR = ~7'b0000000;
					
reg [9:0] NextState;
	
always @( posedge clk) begin
	NextState[9:7] = leftLight;
	NextState[2:0] = rightLight;
	HEX2 = NOERROR;
case (NextState)
	IDLE: 	if (right)
					if (left) begin
						HEX2 = ERROR;
						leftLight = 3'b000; 
						rightLight = 3'b000;
						NextState = IDLE; end
					else begin
						leftLight = 3'b000;
						rightLight = 3'b100;
						HEX2 = NOERROR;
						NextState = R1; end
				else if (left)
					if (right) begin
						HEX2 = ERROR;
						leftLight = 3'b000;
						rightLight = 3'b000;
						NextState = IDLE; end
					else begin
						HEX2 = NOERROR;
						leftLight = 3'b001;
						rightLight = 3'b000;
						NextState = L1; end
				else begin
					leftLight = 3'b000;
					rightLight = 3'b000;
					NextState = IDLE; end
	 L1:  NextState = L1A;
	 L1A: NextState = L1B;
	 L1B: if (left) begin
				if (right) begin
						HEX2 = ERROR;
						leftLight = 3'b000;
						rightLight = 3'b000;
						NextState = IDLE; end
				else
			leftLight = 3'b011;
			rightLight = 3'b000;
			NextState = L2; end
			else begin
			leftLight = 3'b000;
			rightLight = 3'b000;
			NextState = IDLE; end
	 
	 L2: NextState = L1B;
	 L2A:NextState = L2B;
	 L2B: if (left) begin
				if (right) begin
						HEX2 = ERROR;
						leftLight = 3'b000;
						rightLight = 3'b000;
						NextState = IDLE; end
				else
				leftLight = 3'b111;
				rightLight = 3'b000;
				NextState = L3; end
			else begin
				leftLight = 3'b000;
				rightLight = 3'b000;
				NextState = IDLE; end
	 L3:NextState = L3A;
	 L3A:NextState = L3B;
	 L3B: if(left)	begin
				if (right) begin
						HEX2 = ERROR;
						leftLight = 3'b000;
						rightLight = 3'b000;
						NextState = IDLE; end
				else
					leftLight = 3'b000;
					rightLight = 3'b000;
					NextState = IDLE; end
	 R1: NextState = R1A;
	 R1A:NextState = R1B;
	 R1B: 	if (right) begin
					if (left) begin
							HEX2 = ERROR;
							leftLight = 3'b000;
							rightLight = 3'b000;
							NextState = IDLE; end
					else
					leftLight = 3'b000;
					rightLight = 3'b110;
					NextState = R2; end
			else begin
				leftLight = 3'b000;
				rightLight = 3'b000;
				NextState = IDLE; end
	 R2: NextState = R2A;
	 R2A: NextState = R2B;
	 R2B: 	if (right)begin
					if (left) begin
							HEX2 = ERROR;
							leftLight = 3'b000;
							rightLight = 3'b000;
							NextState = IDLE; end
					else
					leftLight = 3'b000;
					rightLight = 3'b111;
					NextState = R3;end
			else begin
				leftLight = 3'b000;
				rightLight = 3'b000; 
				NextState = IDLE; end
	 R3: NextState = R3A;
	 R3A:NextState = R3B;
	 R3B: if (right) begin
					if (left) begin
							HEX2 = ERROR;
							leftLight = 3'b000;
							rightLight = 3'b000;
							NextState = IDLE; end
					else
					leftLight = 3'b000;
					rightLight = 3'b000;
					NextState = IDLE; end

endcase				
		

end
endmodule
