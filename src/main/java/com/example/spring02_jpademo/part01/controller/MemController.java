package com.example.spring02_jpademo.part01.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring02_jpademo.part01.dto.MemDTO;
import com.example.spring02_jpademo.part01.service.MemService;

@RestController
public class MemController {
	//데이터 타입을 선언할 때 인터페이스로 함. 그래야 종속되어 있는 값들이 유연해짐
	@Autowired
	private MemService memService;
	
	public MemController() {
		
	}
	
	//DTO가 List를 담아져 있고 이걸 Browser로 넘겨야 하니까 제너릭 안에 리스트가 있지
	//http;//localhost:8090/mem/홍길동 -> '홍길동'을 유니코드로 변환해야 함 (아래 참고)
	//http://localhost:8090/mem/%ED%99%8D%EA%B8%B8%EB%8F%99
	@GetMapping(value="/mem/{name}")
	public ResponseEntity<List<MemDTO>> getListName(@PathVariable("name") String name){
		//실제 넘길 데이터는 body에 담아서 넘겨줌
//		return ResponseEntity.ok().body(memService.getByJPQL(name));
//		return ResponseEntity.ok().body(memService.getByCriteria(name));
//		return ResponseEntity.ok().body(memService.getByNativeQuery(name));
		return ResponseEntity.ok().body(memService.getByNamedQuery(name));
	}
	
	//http://localhost:8090/mem/num/1
	@GetMapping(value="/mem/num/{num}")
	public ResponseEntity<List<MemDTO>> getListNum(@PathVariable("num") int num){
		return ResponseEntity.ok().body(memService.getMemByNum(num));
	}
	
	//http://localhost:8090/mem/namenum/홍길동/10
		@GetMapping(value="/mem/namenum/{name}/{age}")
		public ResponseEntity<List<MemDTO>> getListNameAndNum(@PathVariable("name") String name, @PathVariable("age") int age){
			return ResponseEntity.ok().body(memService.getMemByNameAndAge(name,age));
		}
		
		//http://localhost:8090/mem/age
		@GetMapping(value="/mem/age")
		public ResponseEntity<List<MemDTO>> getListAge(){
			return ResponseEntity.ok().body(memService.getMemByAgeIsNull());
		}
		
	//Insert
		//{"name": "김규리", "age":40 ,"loc":"대구"}
		//http://localhost:8090/mem
		@PostMapping(value="/mem")
		public ResponseEntity<Integer> insertMemByNative(@RequestBody MemDTO mem){
			//return ResponseEntity.ok().body(memService.insertMemByNative(mem.getName(), mem.getAge(), mem.getLoc()));
			return ResponseEntity.ok().body(memService.insertMemByNative(mem));
		}
		
		//{"num":8, "name":"이산", "age":34, "loc":"부산"}
		//{"num":9, "name":"이진기", "age":37, "loc":"대구"}
		//	//http://localhost:8090/mem
		@PutMapping(value="/mem")
		public ResponseEntity<Integer> updateMem(@RequestBody MemDTO memDTO){
		return ResponseEntity.ok().body(memService.updateMem(memDTO));
		
		}
		//http://localhost:8090/mem/7
		@DeleteMapping(value="/mem/{num}")
		public ResponseEntity<Integer> dletemem(@PathVariable("num") int num){
			return ResponseEntity.ok().body(memService.deleteMem(num));
			
			}
		
}//end class
