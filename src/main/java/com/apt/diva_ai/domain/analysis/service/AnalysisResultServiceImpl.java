package com.apt.diva_ai.domain.analysis.service;

import com.apt.diva_ai.domain.analysis.entity.AnalysisResult;
import com.apt.diva_ai.domain.analysis.repository.AnalysisResultRepository;
import com.apt.diva_ai.domain.expert.entity.ExpertAnalysis;
import com.apt.diva_ai.domain.expert.service.ExpertAnalysisService;
import com.apt.diva_ai.domain.financial.entity.Financial;
import com.apt.diva_ai.domain.financial.service.FinancialService;
import com.apt.diva_ai.domain.macroeconomics.entity.Macroeconomics;
import com.apt.diva_ai.domain.macroeconomics.service.MacroeconomicsService;
import com.apt.diva_ai.domain.movement.entity.InvestmentMovement;
import com.apt.diva_ai.domain.movement.service.InvestmentMovementService;
import com.apt.diva_ai.domain.news.entity.News;
import com.apt.diva_ai.domain.news.service.NewsService;
import com.apt.diva_ai.domain.stock.entity.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnalysisResultServiceImpl implements AnalysisResultService {

    private final AnalysisResultRepository analysisResultRepository;
    private final ExpertAnalysisService expertAnalysisService;
    private final FinancialService financialService;
    private final MacroeconomicsService macroeconomicsService;
    private final InvestmentMovementService investmentMovementService;
    private final NewsService newsService;

    @Override
    public AnalysisResult upsertResultForExpertAnalysis(Stock stock, String content) {
        AnalysisResult analysisResult = analysisResultRepository.findByStockName(
                stock.getCompanyName())
            .orElse(AnalysisResult.builder().stock(stock).build());

        if (analysisResult.getExpertAnalysis() == null) {
            ExpertAnalysis expertAnalysis = expertAnalysisService.addExpertAnalysis(content);
            analysisResult.setExpertAnalysis(expertAnalysis);
        } else {
            expertAnalysisService.updateContent(
                analysisResult.getExpertAnalysis().getExpertAnalysisId(), content);
        }

        return analysisResultRepository.save(analysisResult);
    }

    @Override
    public AnalysisResult upsertResultForFinancial(Stock stock, String content) {
        AnalysisResult analysisResult = analysisResultRepository.findByStockName(
                stock.getCompanyName())
            .orElse(AnalysisResult.builder().stock(stock).build());

        if (analysisResult.getFinancial() == null) {
            Financial financial = financialService.addFinancial(content);
            analysisResult.setFinancial(financial);
        } else {
            financialService.updateContent(
                analysisResult.getFinancial().getFinancialId(), content);
        }

        return analysisResultRepository.save(analysisResult);
    }

    @Override
    public AnalysisResult upsertResultForMacroeconomics(Stock stock, String content) {
        AnalysisResult analysisResult = analysisResultRepository.findByStockName(
                stock.getCompanyName())
            .orElse(AnalysisResult.builder().stock(stock).build());

        if (analysisResult.getMacroeconomics() == null) {
            Macroeconomics macroeconomics = macroeconomicsService.addMacroeconomics(content);
            analysisResult.setMacroeconomics(macroeconomics);
        } else {
            macroeconomicsService.updateContent(
                analysisResult.getMacroeconomics().getMacroeconomicsId(), content);
        }

        return analysisResultRepository.save(analysisResult);
    }

    @Override
    public AnalysisResult upsertResultForInvestmentMovement(Stock stock, String content) {
        AnalysisResult analysisResult = analysisResultRepository.findByStockName(
                stock.getCompanyName())
            .orElse(AnalysisResult.builder().stock(stock).build());

        if (analysisResult.getInvestmentMovement() == null) {
            InvestmentMovement investmentMovement = investmentMovementService.addInvestmentMovement(
                content);
            analysisResult.setInvestmentMovement(investmentMovement);
        } else {
            investmentMovementService.updateContent(
                analysisResult.getInvestmentMovement().getInvestmentMovementId(), content);
        }

        return analysisResultRepository.save(analysisResult);
    }

    @Override
    public AnalysisResult upsertResultForNews(Stock stock, String content) {
        AnalysisResult analysisResult = analysisResultRepository.findByStockName(
                stock.getCompanyName())
            .orElse(AnalysisResult.builder().stock(stock).build());

        if (analysisResult.getNews() == null) {
            News news = newsService.addNews(
                content);
            analysisResult.setNews(news);
        } else {
            newsService.updateContent(
                analysisResult.getNews().getNewsId(), content);
        }

        return analysisResultRepository.save(analysisResult);
    }
}
