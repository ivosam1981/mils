package com.ivosam.mils.services;

import com.ivosam.mils.domain.ItemPedido;
import com.ivosam.mils.domain.PagamentoComBoleto;
import com.ivosam.mils.domain.Pedido;
import com.ivosam.mils.domain.enums.EstadoPagamento;
import com.ivosam.mils.repositories.ItemPedidoRepository;
import com.ivosam.mils.repositories.PagamentoRepository;
import com.ivosam.mils.repositories.PedidoRepository;
import com.ivosam.mils.repositories.ProdutoRepository;
import com.ivosam.mils.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.perc.PercInstantiator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repoPedido;

    @Autowired
    BoletoService boletoService;

    @Autowired
    private PagamentoRepository repoPagamento;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository repoItemPedido;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;


    public Pedido buscar(Long id){
        Optional<Pedido> obj = repoPedido.findById(id);
        return obj.orElseThrow(()-> new ObjectNotFoundException("Objeto não encontrado! Id:" +id +
                "Tipo " + Pedido.class.getName()));
    }


    public Pedido insert(Pedido obj){
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        obj.getPagamento().setPedido(obj);
        if(obj.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());

        }
        obj = repoPedido.save(obj);
        repoPagamento.save(obj.getPagamento());
        for(ItemPedido ip : obj.getItens()){
            ip.setDesconto(0d);
            ip.setProduto(produtoService.buscar(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        repoItemPedido.saveAll(obj.getItens());
        emailService.sendOrderConfirmationHtmlEmail(obj);
        return obj;
    }
}
