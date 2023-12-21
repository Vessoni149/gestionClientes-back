
package com.gestionClientesBack.Controller;

import com.gestionClientesBack.Repository.IClienteRepository;
import com.gestionClientesBack.model.Cliente;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    
    @Autowired
    private IClienteRepository clienteRepo;
    
    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        return clienteRepo.findAll();
    }
    
    @PostMapping("/clientes")
    public void guardarCliente(@RequestBody Cliente cli){
        clienteRepo.save(cli);
    }
    
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> listarClientePorId(@PathVariable Long id){
        Cliente cli = clienteRepo.findById(id).orElse(null);
        return ResponseEntity.ok(cli);
    }
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cli){
        Cliente cliente = clienteRepo.findById(id).orElse(null);
        cliente.setNombre(cli.getNombre());
        cliente.setApellido(cli.getApellido());
        cliente.setEmail(cli.getEmail());
        clienteRepo.save(cliente);
        return ResponseEntity.ok(cliente);
    }
    
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarCliente(@PathVariable Long id){
        Cliente cliente = clienteRepo.findById(id).orElse(null);
        clienteRepo.delete(cliente);
        Map<String,Boolean> response = new HashMap();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
        
    }
    
}
