	Load_Const	R1, 1
	Store		R1, a
	Load_Mem	R1, a
	Load_Const	R2, 0
	Cmp_GT		R1, R2
	Store		R1, $tmp0
	Load_Mem	R1, $tmp0
	JumpIfZero	R1, lab0
	Load_Const	R1, 2
	Store		R1, a
lab0:
HALT
